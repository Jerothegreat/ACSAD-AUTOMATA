import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import java.io.File;

// Import your algorithm packages
import Collatz.Collatz;
import DivAlgo.DivAlgo;
import Euclidean.Euclidean;
import Fibonacci.Fibonacci;
import Lucas.Lucas;
import Palindrome.Palindrome;
import Tribonacci.Tribonacci;

public class AutomataGUI {

    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    // Modern Color Palette
    private final Color COLOR_BG_FALLBACK = new Color(18, 18, 20);
    private final Color COLOR_OVERLAY = new Color(18, 18, 20, 180);
    private final Color COLOR_CARD_BG = new Color(255, 255, 255);
    private final Color COLOR_TEXT_DARK = new Color(30, 30, 36);
    private final Color COLOR_TEXT_DESC = new Color(90, 90, 98);
    private final Color COLOR_BTN = new Color(17, 31, 46);
    private final Color COLOR_BTN_HOVER = new Color(34, 62, 92);
    private final Color COLOR_INPUT_BG = new Color(244, 244, 246);

    public static void main(String[] args) {
        // Set cross-platform look and feel to ensure custom painting renders cleanly
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            try {
                new AutomataGUI().initialize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void initialize() {
        frame = new JFrame("Recursive Sequence Portfolio");
        frame.setSize(1000, 780);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setBackground(COLOR_BG_FALLBACK);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setOpaque(false);

        // Map the correct images to their respective panels
        cardPanel.add(buildMainMenu(), "MainMenu");
        cardPanel.add(buildAlgoPanel("Division Algorithm",
                "Computes the quotient and remainder for two integers using the formal division algorithm.", null,
                new String[] { "Dividend:", "Divisor:" }, DivAlgo::process), "DivAlgo");
        cardPanel.add(buildAlgoPanel("Euclidean Algorithm",
                "Finds the greatest common divisor (GCD) and least common multiple (LCM) of two integers by repeated division.",
                null, new String[] { "Integer A:", "Integer B:" }, Euclidean::process), "Euclidean");
        cardPanel.add(buildAlgoPanel("Collatz Sequence",
                "Generates a sequence from a positive odd integer. If odd, multiply by 3 and add 1. If even, divide by 2, until reaching 1.",
                null, new String[] { "Positive Odd Integer:" }, Collatz::process), "Collatz");

        cardPanel.add(buildAlgoPanel("Fibonacci Numbers",
                "A recursive sequence where F(0)=0, F(1)=1, and F(n) = F(n-1) + F(n-2) for n >= 2.", "2.jpg",
                new String[] { "Number of terms (n > 2):" }, Fibonacci::process), "Fibonacci");
        cardPanel.add(buildAlgoPanel("Lucas Numbers",
                "A recursive sequence where L(0)=2, L(1)=1, and L(n) = L(n-1) + L(n-2) for n >= 2.", "3.jpg",
                new String[] { "Number of terms (n > 2):" }, Lucas::process), "Lucas");
        cardPanel.add(buildAlgoPanel("Tribonacci Numbers",
                "A recursive sequence where T(0)=0, T(1)=0, T(2)=1, and T(n) = T(n-1) + T(n-2) + T(n-3) for n >= 3.",
                "4.jpg", new String[] { "Number of terms (n > 3):" }, Tribonacci::process), "Tribonacci");

        cardPanel.add(buildAlgoPanel("Palindrome Checker",
                "Evaluates a string to check if it reads the same forwards and backwards, ignoring spaces and capitalization.",
                null, new String[] { "Enter String:" }, Palindrome::process), "Palindrome");

        frame.add(cardPanel);
        frame.setVisible(true);
    }

    private JPanel buildMainMenu() {
        BackgroundPanel menuPanel = new BackgroundPanel();
        menuPanel.setLayout(new GridBagLayout());

        ShadowedRoundedPanel card = new ShadowedRoundedPanel(30, new BorderLayout(0, 20));
        card.setBackground(COLOR_CARD_BG);
        card.setBorder(new EmptyBorder(40, 50, 40, 50));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);

        JLabel title = new JLabel("Recursive Sequence");
        title.setFont(new Font("SansSerif", Font.BOLD, 36));
        title.setForeground(COLOR_TEXT_DARK);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Developed by: Avril Tiffany Jumawan, Hazel Ann Alampayan, Sean Steven Bautista");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 15));
        subtitle.setForeground(COLOR_TEXT_DESC);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Load 1.jpg for the main menu header
        JLabel heroImage = createScaledImageLabel("1.jpg", 550, 180);
        if (heroImage != null) {
            heroImage.setAlignmentX(Component.CENTER_ALIGNMENT);
            headerPanel.add(heroImage);
            headerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        headerPanel.add(title);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        headerPanel.add(subtitle);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        String[] algorithms = {
                "Division Algorithm", "Euclidean Algorithm", "Collatz Sequence",
                "Fibonacci Numbers", "Lucas Numbers", "Tribonacci Numbers", "Palindrome Checker"
        };
        String[] cardNames = {
                "DivAlgo", "Euclidean", "Collatz", "Fibonacci", "Lucas", "Tribonacci", "Palindrome"
        };

        for (int i = 0; i < algorithms.length; i++) {
            ModernButton btn = new ModernButton(algorithms[i], 16);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(380, 48));
            btn.setBackground(COLOR_BTN);
            btn.setForeground(Color.WHITE);
            final String targetCard = cardNames[i];
            btn.addActionListener(e -> cardLayout.show(cardPanel, targetCard));
            buttonPanel.add(btn);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        }

        ModernButton exitBtn = new ModernButton("Exit", 16);
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.setMaximumSize(new Dimension(380, 48));
        exitBtn.setBackground(new Color(201, 42, 42));
        exitBtn.setForeground(Color.WHITE);
        exitBtn.addActionListener(e -> System.exit(0));
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        buttonPanel.add(exitBtn);

        card.add(headerPanel, BorderLayout.NORTH);
        card.add(buttonPanel, BorderLayout.CENTER);

        menuPanel.add(card);
        return menuPanel;
    }

    private JPanel buildAlgoPanel(String titleStr, String descStr, String imagePath, String[] labels,
            AlgoProcessor logic) {
        BackgroundPanel bgPanel = new BackgroundPanel();
        bgPanel.setLayout(new BorderLayout());
        bgPanel.setBorder(new EmptyBorder(40, 50, 40, 50));

        ShadowedRoundedPanel card = new ShadowedRoundedPanel(30, new BorderLayout(20, 25));
        card.setBackground(COLOR_CARD_BG);
        card.setBorder(new EmptyBorder(35, 45, 35, 45));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);

        JLabel title = new JLabel(titleStr);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(COLOR_TEXT_DARK);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea descArea = new JTextArea(descStr);
        descArea.setFont(new Font("SansSerif", Font.PLAIN, 15));
        descArea.setForeground(COLOR_TEXT_DESC);
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setEditable(false);
        descArea.setOpaque(false);
        descArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        headerPanel.add(title);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        headerPanel.add(descArea);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        if (imagePath != null) {
            JLabel specImage = createScaledImageLabel(imagePath, 750, 220);
            if (specImage != null) {
                specImage.setAlignmentX(Component.LEFT_ALIGNMENT);
                headerPanel.add(specImage);
                headerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            }
        }

        JPanel centerPanel = new JPanel(new BorderLayout(15, 20));
        centerPanel.setOpaque(false);

        JPanel inputPanel = new JPanel(new GridLayout(labels.length, 2, 15, 15));
        inputPanel.setOpaque(false);
        JTextField[] textFields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setFont(new Font("SansSerif", Font.BOLD, 15));
            lbl.setForeground(COLOR_TEXT_DARK);

            // Modern Borderless Input Styling
            textFields[i] = new JTextField();
            textFields[i].setFont(new Font("SansSerif", Font.PLAIN, 15));
            textFields[i].setBackground(COLOR_INPUT_BG);
            textFields[i].setBorder(new EmptyBorder(12, 15, 12, 15));
            textFields[i].setCaretColor(COLOR_BTN);

            inputPanel.add(lbl);
            inputPanel.add(textFields[i]);
        }

        JTextArea outputArea = new JTextArea(8, 30);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setEditable(false);
        outputArea.setBackground(COLOR_INPUT_BG);
        outputArea.setBorder(new EmptyBorder(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnPanel.setOpaque(false);

        ModernButton runBtn = new ModernButton("Compute / Generate", 14);
        runBtn.setBackground(COLOR_BTN);
        runBtn.setForeground(Color.WHITE);
        runBtn.addActionListener(e -> {
            String[] inputs = new String[textFields.length];
            for (int i = 0; i < textFields.length; i++)
                inputs[i] = textFields[i].getText();
            try {
                String result = logic.process(inputs);
                outputArea.setText(result);
            } catch (Exception ex) {
                outputArea.setText("Error: " + ex.getMessage());
            }
        });

        ModernButton clearBtn = new ModernButton("Clear", 14);
        clearBtn.setBackground(new Color(228, 228, 231));
        clearBtn.setForeground(COLOR_TEXT_DARK);
        clearBtn.addActionListener(e -> {
            for (JTextField tf : textFields)
                tf.setText("");
            outputArea.setText("");
        });

        ModernButton backBtn = new ModernButton("Back to Menu", 14);
        backBtn.setBackground(new Color(228, 228, 231));
        backBtn.setForeground(COLOR_TEXT_DARK);
        backBtn.addActionListener(e -> {
            for (JTextField tf : textFields)
                tf.setText("");
            outputArea.setText("");
            cardLayout.show(cardPanel, "MainMenu");
        });

        btnPanel.add(runBtn);
        btnPanel.add(clearBtn);
        btnPanel.add(backBtn);

        card.add(headerPanel, BorderLayout.NORTH);
        card.add(centerPanel, BorderLayout.CENTER);
        card.add(btnPanel, BorderLayout.SOUTH);

        bgPanel.add(card, BorderLayout.CENTER);
        return bgPanel;
    }

    // --- UTILITY: Robust Image Loader ---
    private JLabel createScaledImageLabel(String path, int maxWidth, int maxHeight) {
        try {
            // First attempt: Resource stream (Best for compiled classes)
            URL imgURL = AutomataGUI.class.getResource(path);

            // Second attempt: Direct file reference (Fallback for IDE development roots)
            if (imgURL == null) {
                File file = new File(path);
                if (file.exists()) {
                    imgURL = file.toURI().toURL();
                } else {
                    return null; // Image strictly not found
                }
            }

            ImageIcon icon = new ImageIcon(imgURL);
            Image img = icon.getImage();

            int width = icon.getIconWidth();
            int height = icon.getIconHeight();

            if (width > maxWidth) {
                height = (height * maxWidth) / width;
                width = maxWidth;
            }
            if (height > maxHeight) {
                width = (width * maxHeight) / height;
                height = maxHeight;
            }

            Image scaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(scaled));
        } catch (Exception e) {
            return null;
        }
    }

    // --- CUSTOM MODERN COMPONENTS ---

    class BackgroundPanel extends JPanel {
        private Image bgImage;

        public BackgroundPanel() {
            try {
                URL imgURL = AutomataGUI.class.getResource("BG.jpg");
                if (imgURL == null) {
                    File f = new File("BG.jpg");
                    if (f.exists())
                        imgURL = f.toURI().toURL();
                }
                if (imgURL != null)
                    bgImage = new ImageIcon(imgURL).getImage();
            } catch (Exception e) {
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (bgImage != null) {
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            } else {
                g.setColor(COLOR_BG_FALLBACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
            g.setColor(COLOR_OVERLAY);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    class ShadowedRoundedPanel extends JPanel {
        private int radius;

        public ShadowedRoundedPanel(int radius, LayoutManager layout) {
            super(layout);
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw faux drop shadow
            g2.setColor(new Color(0, 0, 0, 25));
            g2.fillRoundRect(5, 8, getWidth() - 10, getHeight() - 10, radius, radius);

            // Draw main card
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 10, getHeight() - 12, radius, radius);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    class ModernButton extends JButton {
        private int radius;

        public ModernButton(String text, int radius) {
            super(text);
            this.radius = radius;
            setFont(new Font("SansSerif", Font.BOLD, 15));
            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setBorder(new EmptyBorder(12, 25, 12, 25));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (getModel().isPressed()) {
                g2.setColor(getBackground().darker());
            } else if (getModel().isRollover()) {
                g2.setColor(COLOR_BTN_HOVER); // Custom hover state override
                if (getBackground().equals(new Color(201, 42, 42)))
                    g2.setColor(new Color(230, 50, 50));
                if (getBackground().equals(new Color(228, 228, 231)))
                    g2.setColor(new Color(210, 210, 215));
            } else {
                g2.setColor(getBackground());
            }

            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    interface AlgoProcessor {
        String process(String[] inputs) throws Exception;
    }
}