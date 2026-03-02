package com.java.puzzle;

import com.github.tjake.jlama.model.AbstractModel;
import com.github.tjake.jlama.model.functions.Generator;
import com.github.tjake.jlama.safetensors.DType;
import com.github.tjake.jlama.safetensors.prompt.PromptContext;
import com.github.tjake.jlama.util.Downloader;
import dev.langchain4j.model.chat.response.ChatResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ResourceLoader;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.jlama.JlamaChatModel;

import java.io.File;
import java.util.*;

import static com.github.tjake.jlama.model.ModelSupport.loadModel;

@RestController
public class ChatController {
    private final ResourceLoader resourceLoader;

    public ChatController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    @PostMapping("/chat")
    public ChatResult generateCustomChat(@RequestBody ChatRequest request) throws Exception{
        String model = "tjake/Llama-3.2-1B-Instruct-JQ4";
        String workingDirectory = resourceLoader.getResource("classpath:models").getFile().getAbsolutePath();

        String prompt = request.prompt();

        // Downloads the model or just returns the local path if it's already downloaded
        File localModelPath = new Downloader(workingDirectory, model).huggingFaceModel();

        // Loads the quantized model and specified use of quantized memory
        Generator.Response r;
        try (AbstractModel m = loadModel(localModelPath, DType.F32, DType.I8)) {

            PromptContext ctx;
            // Checks if the model supports chat prompting and adds prompt in the expected format for this model
            if (m.promptSupport().isPresent()) {
                ctx = m.promptSupport()
                        .get()
                        .builder()
                        .addSystemMessage("You are a helpful chatbot who writes short responses.")
                        .addUserMessage(prompt)
                        .build();
            } else {
                ctx = PromptContext.of(prompt);
            }

            System.out.printf("Prompt: %s\n%n", ctx.getPrompt());
            r = m.generate(UUID.randomUUID(), ctx, 0.0f, 256, (s, f) -> {
            });
        }
        System.out.println(r.responseText);
        return new ChatResult(r.responseText);
    }

    @PostMapping("/langchain-chat")
    public ChatResult generateLangChainCustomChat(@RequestBody ChatRequest request) throws Exception{
        ChatModel model = JlamaChatModel.builder()
                .modelName("tjake/Llama-3.2-1B-Instruct-JQ4")
                .temperature(0.3f)
                .build();

        ChatResponse chatResponse = model.chat(
                SystemMessage.from("You are helpful chatbot who is a java expert."),
                UserMessage.from(request.prompt())
        );

        System.out.printf("\n%s\n%n", chatResponse.aiMessage().text());
        return new ChatResult(chatResponse.aiMessage().text());
    }

}
