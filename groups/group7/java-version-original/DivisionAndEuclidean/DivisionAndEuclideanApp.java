import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class DivisionAndEuclideanApp {

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

    static CardLayout cardLayout = new CardLayout();
    static JPanel cardPanel = new JPanel(cardLayout);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    static void createAndShowGUI() {
        JFrame frame = new JFrame("Division & Euclidean Algorithms");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 680);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        cardPanel.setOpaque(false);

        cardPanel.add(createMenuPanel(), "MENU");
        cardPanel.add(createAlgorithmPanel("Division",
                "The Division Algorithm computes the integer quotient and integer remainder when one integer is divided by another."), "DIVISION");
        cardPanel.add(createAlgorithmPanel("Euclidean",
                "The Euclidean Algorithm iterates the Division Algorithm to find the Greatest Common Divisor (GCD) and Least Common Multiple (LCM) of two integers."), "EUCLIDEAN");

        JPanel mainContainer = new JPanel(new BorderLayout()) {
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

        mainContainer.add(cardPanel, BorderLayout.CENTER);
        frame.setContentPane(mainContainer);
        frame.setVisible(true);
    }

    static JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel headerLabel = new JLabel("Division & Euclidean");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        headerLabel.setForeground(ACCENT);
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Please choose an algorithm to compute.");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(TEXT_DIM);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(headerLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(subtitleLabel);
        panel.add(Box.createVerticalStrut(50));

        JButton btnDiv = createStyledButton("1) Division Algorithm", ACCENT, BG_DARK, true);
        btnDiv.setMaximumSize(new Dimension(300, 50));
        btnDiv.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDiv.addActionListener(e -> cardLayout.show(cardPanel, "DIVISION"));

        JButton btnEuc = createStyledButton("2) Euclidean Algorithm", ACCENT, BG_DARK, true);
        btnEuc.setMaximumSize(new Dimension(300, 50));
        btnEuc.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEuc.addActionListener(e -> cardLayout.show(cardPanel, "EUCLIDEAN"));

        JButton btnExit = createStyledButton("3) Exit", BORDER_COLOR, TEXT_PRIMARY, false);
        btnExit.setMaximumSize(new Dimension(300, 50));
        btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExit.addActionListener(e -> System.exit(0));

        panel.add(btnDiv);
        panel.add(Box.createVerticalStrut(15));
        panel.add(btnEuc);
        panel.add(Box.createVerticalStrut(15));
        panel.add(btnExit);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    static JPanel createAlgorithmPanel(String name, String description) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel headerLabel = new JLabel(name + " Algorithm");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        headerLabel.setForeground(ACCENT);
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea descArea = new JTextArea(description);
        descArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descArea.setForeground(TEXT_DIM);
        descArea.setOpaque(false);
        descArea.setEditable(false);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        descArea.setMaximumSize(new Dimension(600, 60));

        panel.add(headerLabel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(descArea);
        panel.add(Box.createVerticalStrut(25));

        // Input Card
        RoundedPanel inputCard = new RoundedPanel(18, BG_CARD);
        inputCard.setLayout(new BoxLayout(inputCard, BoxLayout.Y_AXIS));
        inputCard.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        inputCard.setMaximumSize(new Dimension(650, 150));
        inputCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel inputLabel = new JLabel("Enter two positive integers:");
        inputLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        inputLabel.setForeground(TEXT_PRIMARY);
        inputLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel inputRow = new JPanel();
        inputRow.setLayout(new BoxLayout(inputRow, BoxLayout.X_AXIS));
        inputRow.setOpaque(false);
        inputRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputRow.setMaximumSize(new Dimension(600, 50));

        JTextField inputField1 = createStyledTextField();
        JTextField inputField2 = createStyledTextField();

        JButton btnCompute = createStyledButton("Compute", ACCENT, BG_DARK, true);
        JButton btnBack = createStyledButton("Back", BORDER_COLOR, TEXT_PRIMARY, false);
        
        btnCompute.setMaximumSize(new Dimension(100, 40));
        btnBack.setMaximumSize(new Dimension(80, 40));

        inputRow.add(new JLabel("Int 1: ") {{ setForeground(TEXT_DIM); setFont(new Font("Segoe UI", Font.PLAIN, 14)); }});
        inputRow.add(inputField1);
        inputRow.add(Box.createHorizontalStrut(15));
        inputRow.add(new JLabel("Int 2: ") {{ setForeground(TEXT_DIM); setFont(new Font("Segoe UI", Font.PLAIN, 14)); }});
        inputRow.add(inputField2);
        inputRow.add(Box.createHorizontalStrut(20));
        inputRow.add(btnCompute);
        inputRow.add(Box.createHorizontalStrut(10));
        inputRow.add(btnBack);

        inputCard.add(inputLabel);
        inputCard.add(Box.createVerticalStrut(15));
        inputCard.add(inputRow);

        panel.add(inputCard);
        panel.add(Box.createVerticalStrut(25));

        // Output Card
        RoundedPanel outputCard = new RoundedPanel(18, BG_CARD);
        outputCard.setLayout(new BorderLayout());
        outputCard.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        outputCard.setMaximumSize(new Dimension(650, 250));
        outputCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel outputTitle = new JLabel("  Output");
        outputTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        outputTitle.setForeground(TEXT_DIM);
        CircleDotIcon statusIcon = new CircleDotIcon(SUCCESS_COLOR, 8);
        outputTitle.setIcon(statusIcon);

        JTextArea outputArea = new JTextArea("Waiting for input...");
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 15));
        outputArea.setForeground(TEXT_PRIMARY);
        outputArea.setBackground(BG_INPUT);
        outputArea.setCaretColor(ACCENT);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setMargin(new Insets(12, 14, 12, 14));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(new RoundedBorder(12, BORDER_COLOR));
        scrollPane.getViewport().setBackground(BG_INPUT);
        scrollPane.setPreferredSize(new Dimension(600, 180));

        outputCard.add(outputTitle, BorderLayout.NORTH);
        outputCard.add(Box.createVerticalStrut(10), BorderLayout.CENTER);
        outputCard.add(scrollPane, BorderLayout.SOUTH);

        panel.add(outputCard);
        panel.add(Box.createVerticalGlue());

        // Actions
        btnBack.addActionListener(e -> {
            inputField1.setText("");
            inputField2.setText("");
            outputArea.setText("Waiting for input...");
            outputArea.setForeground(TEXT_PRIMARY);
            statusIcon.setColor(SUCCESS_COLOR);
            outputTitle.repaint();
            cardLayout.show(cardPanel, "MENU");
        });

        ActionListener computeAction = e -> {
            String raw1 = inputField1.getText().trim();
            String raw2 = inputField2.getText().trim();

            if (raw1.isEmpty() || raw2.isEmpty()) {
                showError(outputArea, statusIcon, outputTitle, "Please enter both values.");
                return;
            }

            if (raw1.contains(".") || raw2.contains(".")) {
                showError(outputArea, statusIcon, outputTitle, "Decimals are not allowed. Please enter whole numbers.");
                return;
            }

            if (!raw1.matches("\\d+") || !raw2.matches("\\d+")) {
                if (raw1.startsWith("-") || raw2.startsWith("-"))
                    showError(outputArea, statusIcon, outputTitle, "Negative numbers are not allowed. Please enter positive whole numbers.");
                else
                    showError(outputArea, statusIcon, outputTitle, "Invalid input. Only positive whole numbers are accepted (no letters or symbols).");
                return;
            }

            int num1, num2;
            try {
                num1 = Integer.parseInt(raw1);
                num2 = Integer.parseInt(raw2);
            } catch (NumberFormatException ex) {
                showError(outputArea, statusIcon, outputTitle, "Number is too large.");
                return;
            }

            if (num1 <= 0 || num2 <= 0) {
                showError(outputArea, statusIcon, outputTitle, "Integers must be positive (greater than 0).");
                return;
            }

            int m = Math.max(num1, num2);
            int n = Math.min(num1, num2);

            StringBuilder sb = new StringBuilder();
            sb.append("SOLUTION:\n");

            if (name.equals("Division")) {
                int q = m / n;
                int r = m % n;
                sb.append(m).append(" = ").append(n).append("(").append(q).append(") + ").append(r).append("\n");
                sb.append("The dividend is ").append(m).append("\n");
                sb.append("The divisor is ").append(n).append("\n");
                sb.append("The quotient is ").append(q).append(" and the remainder is ").append(r);
            } else if (name.equals("Euclidean")) {
                int originalM = m;
                int originalN = n;
                int gcd = n;
                
                while (true) {
                    int q = m / n;
                    int r = m % n;
                    if (r == 0) {
                        sb.append(m).append(" = ").append(n).append("(").append(q).append(")\n");
                        gcd = n;
                        break;
                    } else {
                        sb.append(m).append(" = ").append(n).append("(").append(q).append(") + ").append(r).append("\n");
                    }
                    m = n;
                    n = r;
                }
                
                long lcm = (long) originalM * originalN / gcd;
                sb.append("\nThe integers are ").append(originalM).append(" and ").append(originalN).append("\n");
                sb.append("The greatest common divisor of ").append(originalM).append(" and ").append(originalN).append(" is ").append(gcd).append("\n");
                sb.append("The least common multiple of ").append(originalM).append(" and ").append(originalN).append(" is ").append(lcm).append(".");
            }

            statusIcon.setColor(SUCCESS_COLOR);
            outputTitle.repaint();
            outputArea.setForeground(SUCCESS_COLOR);
            outputArea.setText(sb.toString());
        };

        btnCompute.addActionListener(computeAction);
        inputField1.addActionListener(computeAction);
        inputField2.addActionListener(computeAction);

        return panel;
    }

    static JTextField createStyledTextField() {
        JTextField field = new JTextField() {
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
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setForeground(TEXT_PRIMARY);
        field.setCaretColor(ACCENT);
        field.setOpaque(false);
        field.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(12, BORDER_COLOR),
                BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));
        field.setMaximumSize(new Dimension(140, 40));
        field.setPreferredSize(new Dimension(140, 40));
        return field;
    }

    static void showError(JTextArea area, CircleDotIcon icon, JLabel title, String msg) {
        icon.setColor(ERROR_COLOR);
        title.repaint();
        area.setForeground(ERROR_COLOR);
        area.setText("INVALID INPUT\nReason: " + msg);
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
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(fgColor);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    static class RoundedPanel extends JPanel {
        int radius;
        Color bgColor;
        RoundedPanel(int radius, Color bgColor) {
            this.radius = radius;
            this.bgColor = bgColor;
            setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    static class RoundedBorder extends AbstractBorder {
        int radius;
        Color color;
        RoundedBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(4, 8, 4, 8);
        }
    }

    static class CircleDotIcon implements Icon {
        Color color;
        int size;
        CircleDotIcon(Color color, int size) {
            this.color = color;
            this.size = size;
        }
        void setColor(Color c) { this.color = c; }
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fillOval(x, y + 2, size, size);
            g2.dispose();
        }
        @Override public int getIconWidth() { return size; }
        @Override public int getIconHeight() { return size; }
    }
}
