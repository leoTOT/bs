package com.monitor.d502.uitl;

import java.awt.Color;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

@Service
public class ValidatePin {
	
	public static String SESSION_CHECK_CODE="session_check_code";

	public static boolean ValidatePIN(HttpSession session, String pin) {
		Object codeobj = session.getAttribute(SESSION_CHECK_CODE);
		if (codeobj != null && pin.toUpperCase().equals(((String) codeobj).toUpperCase())) {
			return true;
		}
		return false;
	}

	public static Color GetRandColor(int s, int e) {
		Random random = new Random();
		if (s > 255)
			s = 255;
		if (e > 255)
			e = 255;
		int r = s + random.nextInt(e - s);
		int g = s + random.nextInt(e - s);
		int b = s + random.nextInt(e - s);
		return new Color(r, g, b);
	}
	

}
