package jp.co.seattle.library.controller;

import java.util.Locale;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.seattle.library.dto.UserInfo;

public class PasswordResetController {

	
	
	@RequestMapping(value = "/newPassword", method = RequestMethod.POST) 
	public String resetPassword(Model model) {
		return "resetPassword";
	}

	/**
	 * 新規アカウント作成
	 *
	 * @param email            メールアドレス
	 * @param password         パスワード
	 * @param passwordForCheck 確認用パスワード
	 * @param model
	 * @return ホーム画面に遷移
	 */
	@Transactional
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String resetPassword(Locale locale, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("passwordForCheck") String passwordForCheck,
			Model model) {
	

	
		if (password.length() >= 8 && password.matches("^[0-9a-zA-Z]+$")) {
			if (password.equals(passwordForCheck)) {
				// パラメータで受け取ったアカウント情報をDtoに格納する。
				UserInfo userInfo = new UserInfo();
				userInfo.setPassword(password);
			
				return "redirect:/login";
			} else {
				model.addAttribute("errorMessage", "パスワードと確認用パスワードが一致しません。");
				return "resetPassword";

			}
		} else {
			model.addAttribute("errorMessage", "パスワードは8桁以上の半角英数字に設定してください。");
		return "resetPassword";
		}
	}
}


