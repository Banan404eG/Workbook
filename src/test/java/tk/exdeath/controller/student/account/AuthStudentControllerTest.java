package tk.exdeath.controller.student.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthStudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void statusIsOk() throws Exception {
        this.mockMvc.perform(get("/authStudent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Введите логин")))
                .andExpect(content().string(containsString("Введите пароль")));
    }

    @Test
    public void correctStudent() throws Exception {
        this.mockMvc.perform(post("/authStudent").param("login", "Ivan").param("password", "1234"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/accountStudent"));
    }

    @Test
    public void incorrectLogin() throws Exception {
        this.mockMvc.perform(post("/authStudent").param("login", "null").param("password", "1234"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Аккаунта с таким логином не существует")))
                .andExpect(content().string(containsString("Введите логин")))
                .andExpect(content().string(containsString("Введите пароль")));
    }

    @Test
    public void incorrectPassword() throws Exception {
        this.mockMvc.perform(post("/authStudent").param("login", "Ivan").param("password", "null"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Неверный пароль, попробуйте ещё раз")))
                .andExpect(content().string(containsString("Введите логин")))
                .andExpect(content().string(containsString("Введите пароль")));
    }
}