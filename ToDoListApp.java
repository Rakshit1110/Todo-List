import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ToDoListApp extends JFrame {
    private JPanel taskPanel;
    private JTextField taskField;

    public ToDoListApp() {
        setTitle("To-Do List");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setPreferredSize(new Dimension(380, 300));

        taskField = new JTextField(20);

        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(new AddButtonListener());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(taskField);
        inputPanel.add(addButton);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                JPanel taskItemPanel = new JPanel();
                taskItemPanel.setLayout(new BorderLayout());

                JCheckBox checkBox = new JCheckBox(task);
                taskItemPanel.add(checkBox, BorderLayout.CENTER);

                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(new DeleteButtonListener(taskItemPanel));
                taskItemPanel.add(deleteButton, BorderLayout.EAST);

                taskPanel.add(taskItemPanel);
                taskPanel.revalidate();
                taskField.setText("");
            }
        }
    }

    private class DeleteButtonListener implements ActionListener {
        private JPanel taskItemPanel;

        public DeleteButtonListener(JPanel taskItemPanel) {
            this.taskItemPanel = taskItemPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            taskPanel.remove(taskItemPanel);
            taskPanel.revalidate();
            taskPanel.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ToDoListApp app = new ToDoListApp();
            app.setVisible(true);
        });
    }
}
