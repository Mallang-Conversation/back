package com.mallang.game;

import java.io.*;
import java.util.stream.Collectors;
import org.springframework.core.io.*;

public class PromptLoader {
  private final ResourceLoader resourceLoader = new DefaultResourceLoader();

  public String loadPrompt(String fileName) {
    Resource resource = resourceLoader.getResource("classpath:prompts/" + fileName);
    try (InputStream in = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
      return reader.lines().collect(Collectors.joining("\n"));
    } catch (IOException e) {
      throw new UncheckedIOException("프롬프트 파일 로드 실패: " + fileName, e);
    }
  }
}