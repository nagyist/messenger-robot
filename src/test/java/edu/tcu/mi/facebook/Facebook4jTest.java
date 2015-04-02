package edu.tcu.mi.facebook;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import facebook4j.Category;
import facebook4j.Comment;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.PagableList;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.User;
import facebook4j.auth.AccessToken;

public class Facebook4jTest {

	@Test
	public void testJarvis() throws FacebookException {
		String robotName = "[Jarvis] : ";
		
		String token = "CAAXN92Ib1qABAKocQY4ZBodEfyJqKlABEV67sbFZBZBmCpCmMJiuHTZAb5WZCxpuKPLaZCp6KdFMQrwtdrSykZB8jia8H8oeFVZBY3YjvgOZCkhNrxr7QbdyrWGZCbcVchlAzC8dHpOtuweuLjTS1TpyTNhtnr1dfFZCipc11LtxiKfC2aZAQAZCi0Wtiucg2p2q6ZACPWcZCWZA7f4UZCaisRoZCTdweuxAvMLmjZACbcZD";
		long expires = 5183999;	
		AccessToken accessToken = new AccessToken(token, expires);
		Facebook facebook = new FacebookFactory().getInstance(accessToken );
		try {
			int index = 0;
			while(true){
				ResponseList<Post> posts = facebook.getPosts();
				Post post = posts.get(index);
				String message = post.getMessage();
				Category _from = post.getFrom();
				User _user = facebook.getUser(_from.getId());
				/** Have a request.*/
				if(message.contains("Jarvis") || message.contains("賈維斯")){
					String postId = post.getId();
					PagableList<Comment> comments = post.getComments();
					/** Response the request.*/
					if(comments.isEmpty()){
						facebook.commentPost(postId, robotName + "Hello, " + _user.getMiddleName() + " 有什麼能為你服務的嗎?");
					} else {
						/** Next conversation. */
						Comment _comment = comments.get(comments.size()-1);
						String _message = _comment.getMessage();
						if(!_message.contains(robotName)){
							/** Somebody asked.*/
							if(_message.contains("time")){
								SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
								/** Jarvis answer.*/
								facebook.commentPost(postId, robotName + "現在時間:" + sdf.format(new Date()));
							}
							if(_message.contains("what date today") || _message.contains("今天幾號") || _message.contains("今天日期")){
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd E");
								/** Jarvis answer.*/
								facebook.commentPost(postId, robotName + "今天日期 " + sdf.format(new Date()));
							}
						} else {
							/** Jarvis said.*/
						}
					}
				} else {
					index++;
				}
			}
		} catch (FacebookException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		System.out.println(sdf.format(new Date()));
	}

}
