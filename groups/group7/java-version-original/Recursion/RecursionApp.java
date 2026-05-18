import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.math.BigInteger;

public class RecursionApp {

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
        JFrame frame = new JFrame("Recursive Sequences");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 650);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        cardPanel.setOpaque(false);

        cardPanel.add(createMenuPanel(), "MENU");
        cardPanel.add(createSequencePanel("Fibonacci",
                "The Fibonacci numbers are a sequence in which each number is the sum of the two preceding ones. Starting from 0 and 1, the sequence goes: 0, 1, 1, 2, 3, 5, 8, and so on. It appears frequently in nature and mathematics.",
                2, new long[]{0, 1}), "FIBONACCI");
        cardPanel.add(createSequencePanel("Lucas",
                "The Lucas numbers share the same recursive relationship as the Fibonacci sequence, where each term is the sum of the previous two. However, the Lucas sequence starts with 2 and 1, resulting in: 2, 1, 3, 4, 7, 11, etc.",
                2, new long[]{2, 1}), "LUCAS");
        cardPanel.add(createSequencePanel("Tribonacci",
                "The Tribonacci sequence is a generalization of the Fibonacci sequence where each term is the sum of the three preceding terms. Starting with 0, 0, and 1, the sequence proceeds: 0, 0, 1, 1, 2, 4, 7, 13, etc.",
                3, new long[]{0, 0, 1}), "TRIBONACCI");

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

        JLabel headerLabel = new JLabel("Recursive Sequences");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        headerLabel.setForeground(ACCENT);
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Please choose a sequence to compute.");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(TEXT_DIM);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(headerLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(subtitleLabel);
        panel.add(Box.createVerticalStrut(50));

        JButton btnFib = createStyledButton("1) Fibonacci numbers", ACCENT, BG_DARK, true);
        btnFib.setMaximumSize(new Dimension(300, 50));
        btnFib.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnFib.addActionListener(e -> cardLayout.show(cardPanel, "FIBONACCI"));

        JButton btnLuc = createStyledButton("2) Lucas numbers", ACCENT, BG_DARK, true);
        btnLuc.setMaximumSize(new Dimension(300, 50));
        btnLuc.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLuc.addActionListener(e -> cardLayout.show(cardPanel, "LUCAS"));

        JButton btnTri = createStyledButton("3) Tribonacci numbers", ACCENT, BG_DARK, true);
        btnTri.setMaximumSize(new Dimension(300, 50));
        btnTri.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnTri.addActionListener(e -> cardLayout.show(cardPanel, "TRIBONACCI"));

        JButton btnExit = createStyledButton("4) Exit", BORDER_COLOR, TEXT_PRIMARY, false);
        btnExit.setMaximumSize(new Dimension(300, 50));
        btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExit.addActionListener(e -> System.exit(0));

        panel.add(btnFib);
        panel.add(Box.createVerticalStrut(15));
        panel.add(btnLuc);
        panel.add(Box.createVerticalStrut(15));
        panel.add(btnTri);
        panel.add(Box.createVerticalStrut(15));
        panel.add(btnExit);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    static JPanel createSequencePanel(String name, String description, int minInput, long[] initialTerms) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel headerLabel = new JLabel(name + " Numbers");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        headerLabel.setForeground(ACCENT);
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea descArea = new JTextArea(description + "\n\n(Note: " + name + " mathematical image illustration goes here)");
        descArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descArea.setForeground(TEXT_DIM);
        descArea.setOpaque(false);
        descArea.setEditable(false);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        descArea.setMaximumSize(new Dimension(600, 100));

        panel.add(headerLabel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(descArea);
        panel.add(Box.createVerticalStrut(25));

        // Input Card
        RoundedPanel inputCard = new RoundedPanel(18, BG_CARD);
        inputCard.setLayout(new BoxLayout(inputCard, BoxLayout.Y_AXIS));
        inputCard.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        inputCard.setMaximumSize(new Dimension(600, 120));
        inputCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel inputLabel = new JLabel("Input the number of terms (greater than " + minInput + "):");
        inputLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        inputLabel.setForeground(TEXT_PRIMARY);
        inputLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel inputRow = new JPanel();
        inputRow.setLayout(new BoxLayout(inputRow, BoxLayout.X_AXIS));
        inputRow.setOpaque(false);
        inputRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputRow.setMaximumSize(new Dimension(550, 42));

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
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        inputField.setForeground(TEXT_PRIMARY);
        inputField.setCaretColor(ACCENT);
        inputField.setOpaque(false);
        inputField.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(12, BORDER_COLOR),
                BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));
        inputField.setMaximumSize(new Dimension(250, 40));
        inputField.setPreferredSize(new Dimension(250, 40));

        JButton btnCompute = createStyledButton("Compute", ACCENT, BG_DARK, true);
        JButton btnBack = createStyledButton("Back to Menu", BORDER_COLOR, TEXT_PRIMARY, false);

        inputRow.add(inputField);
        inputRow.add(Box.createHorizontalStrut(15));
        inputRow.add(btnCompute);
        inputRow.add(Box.createHorizontalStrut(10));
        inputRow.add(btnBack);

        inputCard.add(inputLabel);
        inputCard.add(Box.createVerticalStrut(10));
        inputCard.add(inputRow);

        panel.add(inputCard);
        panel.add(Box.createVerticalStrut(25));

        // Output Card
        RoundedPanel outputCard = new RoundedPanel(18, BG_CARD);
        outputCard.setLayout(new BorderLayout());
        outputCard.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        outputCard.setMaximumSize(new Dimension(600, 220));
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
        scrollPane.setPreferredSize(new Dimension(550, 150));

        outputCard.add(outputTitle, BorderLayout.NORTH);
        outputCard.add(Box.createVerticalStrut(10), BorderLayout.CENTER);
        outputCard.add(scrollPane, BorderLayout.SOUTH);

        panel.add(outputCard);
        panel.add(Box.createVerticalGlue());

        // Actions
        btnBack.addActionListener(e -> {
            inputField.setText("");
            outputArea.setText("Waiting for input...");
            outputArea.setForeground(TEXT_PRIMARY);
            statusIcon.setColor(SUCCESS_COLOR);
            outputTitle.repaint();
            cardLayout.show(cardPanel, "MENU");
        });

        ActionListener computeAction = e -> {
            String rawInput = inputField.getText().trim();

            // --- Empty check ---
            if (rawInput.isEmpty()) {
                showError(outputArea, statusIcon, outputTitle, "Please enter a value.");
                return;
            }

            // --- Reject decimals (e.g. "3.5") ---
            if (rawInput.contains(".")) {
                showError(outputArea, statusIcon, outputTitle, "Decimals are not allowed. Please enter a whole number.");
                return;
            }

            // --- Reject negatives, leading signs, letters, symbols ---
            // Only pure digit strings are valid (no +/- prefix)
            if (!rawInput.matches("\\d+")) {
                if (rawInput.startsWith("-"))
                    showError(outputArea, statusIcon, outputTitle, "Negative numbers are not allowed. Please enter a positive whole number.");
                else
                    showError(outputArea, statusIcon, outputTitle, "Invalid input. Only positive whole numbers are accepted (no letters, symbols, or decimals).");
                return;
            }

            // --- Parse and check overflow (> Integer.MAX_VALUE) ---
            int n;
            try {
                n = Integer.parseInt(rawInput);
            } catch (NumberFormatException ex) {
                showError(outputArea, statusIcon, outputTitle, "Number is too large. Maximum allowed is 500.");
                return;
            }

            // --- Minimum bound ---
            if (n <= minInput) {
                showError(outputArea, statusIcon, outputTitle, "Input must be greater than " + minInput + ". You entered: " + n);
                return;
            }

            // --- Upper bound cap to prevent long overflow ---
            final int MAX_TERMS = 500;
            if (n > MAX_TERMS) {
                showError(outputArea, statusIcon, outputTitle, "Input exceeds the maximum allowed limit of " + MAX_TERMS + " terms.");
                return;
            }

            // --- Generate sequence ---
            ArrayList<BigInteger> seq = new ArrayList<>();
            for (long val : initialTerms) {
                seq.add(BigInteger.valueOf(val));
            }

            for (int i = initialTerms.length; i < n; i++) {
                BigInteger nextVal = BigInteger.ZERO;
                for (int j = 1; j <= initialTerms.length; j++) {
                    nextVal = nextVal.add(seq.get(i - j));
                }
                seq.add(nextVal);
            }

            StringBuilder sb = new StringBuilder();
            sb.append("This program will find all the terms of the ").append(name).append(" numbers.\n");
            sb.append("\tInput the number of terms: ").append(n).append("\n");
            sb.append("\tThe ").append(name).append(" numbers are: ");
            for (int i = 0; i < n; i++) {
                sb.append(seq.get(i));
                if (i < n - 1) sb.append(", ");
            }

            statusIcon.setColor(SUCCESS_COLOR);
            outputTitle.repaint();
            outputArea.setForeground(SUCCESS_COLOR);
            outputArea.setText(sb.toString());
        };

        btnCompute.addActionListener(computeAction);
        inputField.addActionListener(computeAction);

        return panel;
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
