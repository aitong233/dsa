package com.spadea.xqipao.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class EditTextUtil {


    public static void setInputDecimals(final EditText editText, final int retain) {

        editText.addTextChangedListener(new TextWatcher() {
            public String a = "";
            public boolean isCopy = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (1 == count) {
                    if (0 == start) {
                        a = s.toString();
                        if (".".equals(a)) {
                            s = "0.";
                            a = "0";
                            isCopy = true;
                            editText.setText(s);
                            editText.setSelection(s.length());
                        }
                    } else if (1 == start) {
                        char c = s.charAt(1);
                        if ("0".equals(a) && '.' != (c)) {
                            s = a + "." + c;
                            isCopy = true;
                            editText.setText(s);
                            editText.setSelection(s.length());
                        }
                    }
                    if (s.toString().contains(".")) {
                        if (s.length() - s.toString().indexOf(".") > retain + 1) {
                            s = s.toString().subSequence(0, s.toString().indexOf(".") + retain + 1);
                            isCopy = true;
                            editText.setText(s);
                            editText.setSelection(s.length());
                        }
                    }
                } else if (count > 1) {
                    if (isCopy) {
                        isCopy = false;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

}
