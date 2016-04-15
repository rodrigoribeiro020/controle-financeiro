package view;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class DecimalFormattedField extends JFormattedTextField {

    public static int FLOAT_TYPE = 1;
    public static int DOUBLE_TYPE = 2;

    private DecimalFormat df = new DecimalFormat();

    private int typeOfValue = 1;
    private float fValue = 0.00f;
    private double dValue = 0.00;

    private String sDecimals;
    private String oldValue;

    /**
     * Creates a new instance of DecimalFormattedField
     */
    public DecimalFormattedField() {
        super();
        this.setHorizontalAlignment(JTextField.RIGHT);
        df.applyPattern("#,##0.00; (#,##0.00)");
        applyActions();
    }

    /**
     * Cria um campo formatado para valor monetário.
     *
     * @param typeOfValue tipo do valor do campo [float (default) ou double]
     */
    public DecimalFormattedField(int typeOfValue) {
        super();
        this.typeOfValue = typeOfValue;
        this.setHorizontalAlignment(JTextField.RIGHT);
        df.applyPattern("R$ #,##0.00; R$ (#,##0.00)");
        applyActions();
    }

    /**
     * Cria um campo formatado para valor monetário.
     *
     * @param typeOfValue tipo do valor do campo [float (default) ou double]
     * @param decimals número de casas decimais depois do zero.
     */
    public DecimalFormattedField(int typeOfValue, int decimals) {
        super();
        this.typeOfValue = typeOfValue;
        this.setHorizontalAlignment(JTextField.RIGHT);
        String s = getDecimalPattern(decimals);
        df.applyPattern("R$ #,##0." + s + "; R$ (#,##0." + s + ")");
        System.out.println("\n" + s + "\n");
        applyActions();
    }

    private void applyActions() {

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    transferFocus();
                }
            }
        });

        this.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent evt) {
                thisFocusLost(evt);
            }
        });

        this.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                thisFocusGained(evt);
            }
        });
    }

    public void thisFocusLost(FocusEvent evt) {
        String valor = getText().replaceAll(",", ".");
        if (!valor.equals("")) {
            oldValue = valor;
        }

        
        setValue(valor);
    }

    public void thisFocusGained(FocusEvent fe) {
        this.setText("");
        normalText();
    }

    private void setValue(String value) {
        try {
            dValue = Double.parseDouble(value);

        } catch (Exception e) {
                //dValue = 0.00f; 

            value = oldValue;
        }

        showValue(value);
    }

    /**
     * Mostra o valor formatado monetariamente
     *
     * @param s valor informado no campo
     */
    public void showValue(String s) {
        try {
            this.setText(df.format(new java.math.BigDecimal(s)));
        } catch (Exception e) {
            error("ERRO!");
        }
    }

    /**
     * Retorna o valor inserido no campo.
     *
     * @return fValue float value
     */
    public float getFloatValue() {
        return fValue;
    }

    /**
     * Retorna o valor inserido no campo.
     *
     * @return dValue double value
     */
    public double getDoubleValue() {
        return dValue;
    }

    /**
     * Retorna a quantidade de zeros da casa decimal
     *
     * @return sDecimals quantidade de zeros (ex.: 000 - 3 zeros depois da
     * vírgula)
     */
    private String getDecimalPattern(int nrDecimals) {
        sDecimals = "";
        for (int i = 0; i < nrDecimals; i++) {
            sDecimals = sDecimals + "0";
        }
        return sDecimals;
    }

    /**
     * Mostra o texto em vermelho e no estilo negrito.
     */
    private void error(String text) {
        this.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 11));
        this.setForeground(new java.awt.Color(255, 0, 0));
        this.setText(text);
    }

    /**
     * Volta o texto ao formato original
     */
    private void normalText() {
        this.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11));
        this.setForeground(new java.awt.Color(0, 0, 0));
    }

}
