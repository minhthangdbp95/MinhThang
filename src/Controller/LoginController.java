package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Dao.UserDao;
import Entity.User;
import View.LoginView;
import View.StudentView;

public class LoginController {
    private UserDao userDao;
    private LoginView loginView;
    private StudentView studentView;

    public LoginController(LoginView view) {
        this.loginView = view;
        this.userDao = new UserDao();
        view.addLoginListener(new LoginListener());
    }

    public void showLoginView() {
            loginView.setVisible(true);
        }

        class LoginListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                User user = loginView.getUser();
                if (userDao.checkUser(user)) {
                    // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
                    studentView = new StudentView();
                    StudentController studentController = new StudentController((View.StudentView) studentView);
                    studentController.showStudentView();
                    loginView.setVisible(false);
                } else {
                    loginView.showMessage("username hoặc password không đúng.");
                }
            }
        }
    }

