package CollatzSequence;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;


public class CollatzSequence {

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
        // --- Frame ---
        JFrame frame = new JFrame("Collatz Sequence");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 520);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(BG_DARK);

        // --- Main container ---
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Subtle gradient background
                GradientPaint gp = new GradientPaint(0, 0, BG_DARK, getWidth(), getHeight(), new Color(24, 24, 37));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        // --- Header ---
        JLabel headerLabel = new JLabel("Collatz Sequence");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        headerLabel.setForeground(ACCENT);
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("This program will find all the terms of the Collatz sequence.");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitleLabel.setForeground(TEXT_DIM);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(headerLabel);
        mainPanel.add(Box.createVerticalStrut(6));
        mainPanel.add(subtitleLabel);
        mainPanel.add(Box.createVerticalStrut(22));

        // --- Card Panel for Input ---
        RoundedPanel cardPanel = new RoundedPanel(18, BG_CARD);
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        cardPanel.setMaximumSize(new Dimension(590, 120));
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel inputLabel = new JLabel("Enter the initial value (positive odd integer):");
        inputLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        inputLabel.setForeground(TEXT_PRIMARY);
        inputLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        cardPanel.add(inputLabel);
        cardPanel.add(Box.createVerticalStrut(10));

        // Input row
        JPanel inputRow = new JPanel();
        inputRow.setLayout(new BoxLayout(inputRow, BoxLayout.X_AXIS));
        inputRow.setOpaque(false);
        inputRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputRow.setMaximumSize(new Dimension(540, 42));

        // Styled text field
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

        // Styled buttons
        JButton computeButton = createStyledButton("Compute", ACCENT, BG_DARK, true);
        JButton clearButton = createStyledButton("Clear", BORDER_COLOR, TEXT_PRIMARY, false);

        inputRow.add(inputField);
        inputRow.add(Box.createHorizontalStrut(10));
        inputRow.add(computeButton);
        inputRow.add(Box.createHorizontalStrut(8));
        inputRow.add(clearButton);

        cardPanel.add(inputRow);
        mainPanel.add(cardPanel);
        mainPanel.add(Box.createVerticalStrut(18));

        // --- Output Card ---
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
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 13));
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



        // --- Status icon reference for updating ---
        final CircleDotIcon statusIcon = (CircleDotIcon) outputTitle.getIcon();

        // --- Compute Action ---
        ActionListener computeAction = e -> {
            String rawInput = inputField.getText().trim();
            outputArea.setText("");

            if (rawInput.isEmpty()) {
                statusIcon.setColor(ERROR_COLOR);
                outputTitle.setText("  Output");
                outputTitle.repaint();
                outputArea.setForeground(ERROR_COLOR);
                outputArea.setText("INVALID OUTPUT\nReason: Please enter a value.");
                return;
            }

            if (!rawInput.matches("-?\\d+")) {
                statusIcon.setColor(ERROR_COLOR);
                outputTitle.repaint();
                outputArea.setForeground(ERROR_COLOR);
                outputArea.setText("INVALID OUTPUT\nReason: Input must be a whole number (no letters or symbols allowed).");
                return;
            }

            long n;
            try {
                n = Long.parseLong(rawInput);
            } catch (NumberFormatException ex) {
                statusIcon.setColor(ERROR_COLOR);
                outputTitle.repaint();
                outputArea.setForeground(ERROR_COLOR);
                outputArea.setText("INVALID OUTPUT\nReason: Number is too large to process.");
                return;
            }

            if (n <= 0) {
                statusIcon.setColor(ERROR_COLOR);
                outputTitle.repaint();
                outputArea.setForeground(ERROR_COLOR);
                outputArea.setText("INVALID OUTPUT\nReason: The initial value must be a positive integer (greater than 0).");
                return;
            }

            if (n % 2 == 0) {
                statusIcon.setColor(ERROR_COLOR);
                outputTitle.repaint();
                outputArea.setForeground(ERROR_COLOR);
                outputArea.setText("INVALID OUTPUT\nReason: The initial value must be a positive ODD integer.");
                return;
            }

            // --- Generate Collatz Sequence ---
            StringBuilder sequence = new StringBuilder();
            long current = n;
            int stepCount = 0;

            while (current != 1) {
                sequence.append(current).append(", ");
                if (current % 2 != 0) {
                    current = (3 * current) + 1;
                } else {
                    current = current / 2;
                }
                stepCount++;
            }
            sequence.append(1);
            stepCount++;

            statusIcon.setColor(SUCCESS_COLOR);
            outputTitle.repaint();
            outputArea.setForeground(SUCCESS_COLOR);
            outputArea.setText("The Collatz sequence are: " + sequence.toString()
                + "\n\nTotal steps: " + stepCount);
        };

        computeButton.addActionListener(computeAction);
        inputField.addActionListener(computeAction);

        // --- Clear Action ---
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

    // --- Custom Rounded Button ---
    static JButton createStyledButton(String text, Color bgColor, Color fgColor, boolean isPrimary) {
        JButton button = new JButton(text) {
            boolean hovering = false;

            {
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        hovering = true;
                        repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        hovering = false;
                        repaint();
                    }
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

    // --- Rounded Panel ---
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

    // --- Rounded Border ---
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

    // --- Small circle icon for status ---
    static class CircleDotIcon implements Icon {
        Color color;
        int size;

        CircleDotIcon(Color color, int size) {
            this.color = color;
            this.size = size;
        }

        void setColor(Color c) {
            this.color = c;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fillOval(x, y + 2, size, size);
            g2.dispose();
        }

        @Override
        public int getIconWidth() { return size; }

        @Override
        public int getIconHeight() { return size; }
    }
}