package Palindrome;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class PalindromeApp {

    // --- Color Palette ---
    static final Color BG_DARK = new Color(30, 30, 46);
    static final Color BG_CARD = new Color(45, 45, 65);
    static final Color BG_INPUT = new Color(55, 55, 80);
    static final Color ACCENT = new Color(137, 180, 250);
    static final Color ACCENT_HOVER = new Color(166, 200, 255);
    static final Color TEXT_PRIMARY = new Color(205, 214, 244);
    static final Color TEXT_DIM = new Color(147, 153, 178);
    static final Color ERROR_COLOR = new Color(243, 139, 168);
    static final Color SUCCESS_COLOR = new Color(166, 227, 161);
    static final Color BORDER_COLOR = new Color(88, 91, 112);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    static void createAndShowGUI() {
        JFrame frame = new JFrame("Palindrome Checker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 520);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(BG_DARK);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, BG_DARK, getWidth(), getHeight(), new Color(24, 24, 37));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        JLabel headerLabel = new JLabel("Palindrome Checker");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        headerLabel.setForeground(ACCENT);
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Checks if a string is a palindrome without built-in string methods.");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitleLabel.setForeground(TEXT_DIM);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(headerLabel);
        mainPanel.add(Box.createVerticalStrut(6));
        mainPanel.add(subtitleLabel);
        mainPanel.add(Box.createVerticalStrut(22));

        RoundedPanel cardPanel = new RoundedPanel(18, BG_CARD);
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        cardPanel.setMaximumSize(new Dimension(590, 120));
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel inputLabel = new JLabel("Enter a string:");
        inputLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        inputLabel.setForeground(TEXT_PRIMARY);
        inputLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        cardPanel.add(inputLabel);
        cardPanel.add(Box.createVerticalStrut(10));

        JPanel inputRow = new JPanel();
        inputRow.setLayout(new BoxLayout(inputRow, BoxLayout.X_AXIS));
        inputRow.setOpaque(false);
        inputRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputRow.setMaximumSize(new Dimension(540, 42));

        JTextField inputField = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(BG_INPUT);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputField.setForeground(TEXT_PRIMARY);
        inputField.setCaretColor(ACCENT);
        inputField.setOpaque(false);
        inputField.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(12, BORDER_COLOR),
            BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));
        inputField.setMaximumSize(new Dimension(320, 38));
        inputField.setPreferredSize(new Dimension(320, 38));

        JButton computeButton = createStyledButton("Check", ACCENT, BG_DARK, true);
        JButton clearButton = createStyledButton("Clear", BORDER_COLOR, TEXT_PRIMARY, false);

        inputRow.add(inputField);
        inputRow.add(Box.createHorizontalStrut(10));
        inputRow.add(computeButton);
        inputRow.add(Box.createHorizontalStrut(8));
        inputRow.add(clearButton);

        cardPanel.add(inputRow);
        mainPanel.add(cardPanel);
        mainPanel.add(Box.createVerticalStrut(18));

        RoundedPanel outputCard = new RoundedPanel(18, BG_CARD);
        outputCard.setLayout(new BorderLayout());
        outputCard.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        outputCard.setMaximumSize(new Dimension(590, 250));
        outputCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel outputTitle = new JLabel("  Output");
        outputTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
        outputTitle.setForeground(TEXT_DIM);
        outputTitle.setIcon(new CircleDotIcon(SUCCESS_COLOR, 8));

        JTextArea outputArea = new JTextArea();
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        outputArea.setForeground(TEXT_PRIMARY);
        outputArea.setBackground(BG_INPUT);
        outputArea.setCaretColor(ACCENT);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setMargin(new Insets(12, 14, 12, 14));
        outputArea.setBorder(null);
        outputArea.setText("Waiting for input...");

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(new RoundedBorder(12, BORDER_COLOR));
        scrollPane.getViewport().setBackground(BG_INPUT);
        scrollPane.setPreferredSize(new Dimension(540, 170));

        outputCard.add(outputTitle, BorderLayout.NORTH);
        outputCard.add(Box.createVerticalStrut(8), BorderLayout.CENTER);
        outputCard.add(scrollPane, BorderLayout.SOUTH);

        mainPanel.add(outputCard);

        final CircleDotIcon statusIcon = (CircleDotIcon) outputTitle.getIcon();

        ActionListener computeAction = e -> {
            String rawInput = inputField.getText();
            
            // Manual length calculation to avoid using built-in methods like .length() entirely
            // Or if allowed, charArray.length is an attribute, not a method!
            char[] chars = rawInput.toCharArray();
            int length = 0;
            for (char c : chars) {
                length++;
            }

            if (length == 0) {
                statusIcon.setColor(ERROR_COLOR);
                outputTitle.repaint();
                outputArea.setForeground(ERROR_COLOR);
                outputArea.setText("INVALID INPUT\nReason: Please enter a string.");
                return;
            }

            // Manually build clean array (ignoring spaces/punctuation, converting to lowercase)
            char[] cleanChars = new char[length];
            int cleanLength = 0;

            for (int i = 0; i < length; i++) {
                char c = chars[i];
                // Check if uppercase
                if (c >= 'A' && c <= 'Z') {
                    cleanChars[cleanLength] = (char) (c + 32); // Convert to lowercase manually
                    cleanLength++;
                } 
                // Check if lowercase or digit
                else if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
                    cleanChars[cleanLength] = c;
                    cleanLength++;
                }
            }

            // Manually check palindrome
            boolean isPalindrome = true;
            if (cleanLength == 0) {
                isPalindrome = false; // Only symbols/spaces is not a palindrome
            } else {
                int left = 0;
                int right = cleanLength - 1;
                while (left < right) {
                    if (cleanChars[left] != cleanChars[right]) {
                        isPalindrome = false;
                        break;
                    }
                    left++;
                    right--;
                }
            }

            statusIcon.setColor(SUCCESS_COLOR);
            outputTitle.repaint();
            outputArea.setForeground(SUCCESS_COLOR);
            
            StringBuilder sb = new StringBuilder();
            sb.append("String: \"").append(rawInput).append("\"\n");
            sb.append("Length: ").append(length).append("\n\n");
            
            if (isPalindrome) {
                sb.append("Result: The string IS a palindrome.");
            } else {
                sb.append("Result: The string is NOT a palindrome.");
            }
            
            outputArea.setText(sb.toString());
        };

        computeButton.addActionListener(computeAction);
        inputField.addActionListener(computeAction);

        clearButton.addActionListener(e -> {
            inputField.setText("");
            outputArea.setForeground(TEXT_PRIMARY);
            outputArea.setText("Waiting for input...");
            statusIcon.setColor(SUCCESS_COLOR);
            outputTitle.repaint();
            inputField.requestFocus();
        });

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        inputField.requestFocus();
    }

    // --- Custom UI Components ---
    static JButton createStyledButton(String text, Color bgColor, Color fgColor, boolean isPrimary) {
        JButton button = new JButton(text) {
            boolean hovering = false;
            {
                addMouseListener(new MouseAdapter() {
                    @Override public void mouseEntered(MouseEvent e) { hovering = true; repaint(); }
                    @Override public void mouseExited(MouseEvent e) { hovering = false; repaint(); }
                });
            }
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color bg = hovering ? (isPrimary ? ACCENT_HOVER : new Color(70, 70, 95)) : bgColor;
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.setFont(getFont());
                g2.setColor(isPrimary ? (hovering ? new Color(30, 30, 46) : fgColor) : fgColor);
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(fgColor);
        button.setPreferredSize(new Dimension(100, 38));
        button.setMaximumSize(new Dimension(100, 38));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    static class RoundedPanel extends JPanel {
        int radius; Color bgColor;
        RoundedPanel(int radius, Color bgColor) { this.radius = radius; this.bgColor = bgColor; setOpaque(false); }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor); g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius); g2.dispose();
            super.paintComponent(g);
        }
    }

    static class RoundedBorder extends AbstractBorder {
        int radius; Color color;
        RoundedBorder(int radius, Color color) { this.radius = radius; this.color = color; }
        @Override public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color); g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius); g2.dispose();
        }
        @Override public Insets getBorderInsets(Component c) { return new Insets(4, 8, 4, 8); }
    }

    static class CircleDotIcon implements Icon {
        Color color; int size;
        CircleDotIcon(Color color, int size) { this.color = color; this.size = size; }
        void setColor(Color c) { this.color = c; }
        @Override public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color); g2.fillOval(x, y + 2, size, size); g2.dispose();
        }
        @Override public int getIconWidth() { return size; }
        @Override public int getIconHeight() { return size; }
    }
}
