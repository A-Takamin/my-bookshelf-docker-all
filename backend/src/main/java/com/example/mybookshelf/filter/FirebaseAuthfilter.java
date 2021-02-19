package com.example.mybookshelf.filter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

@Component
@CrossOrigin
public class FirebaseAuthfilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	    // 初期化処理を行う。このメソッドはアプリケーション起動時に呼び出される。
		try{
			// Firebaseと連携する設定
			FileInputStream serviceAccount = new FileInputStream(".firebase.json");
			
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();
					
			FirebaseApp.initializeApp(options);
		} catch( IOException e) {
			//ログを出力したい。。
		}
		
	}
	
	@Override
	@CrossOrigin
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		try {
			HttpServletRequest httpServReq = HttpServletRequest.class.cast(request);
			// OPTIONS以外のリクエストメソッドはfirebaseで認証する
			if(!httpServReq.getMethod().equals("OPTIONS")) {
				String idToken = httpServReq.getHeader("Authorization");
				FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
				String uid = decodedToken.getUid();
				// ログ
			}
			
		} catch (FirebaseAuthException e) {
			// ログ
		   // Servletに行かせない。
			return;
		}
	    // 後続処理を呼び出す
		filterChain.doFilter(request, response);
	  }


}
