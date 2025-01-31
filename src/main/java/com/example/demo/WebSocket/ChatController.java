package com.example.demo.WebSocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
	
	@GetMapping("/chats")
	public String chats() {
		return "chats";
	}
	
}
