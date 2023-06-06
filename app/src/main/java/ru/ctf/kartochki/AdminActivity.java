package ru.ctf.kartochki;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import java.security.MessageDigest;
import java.util.regex.Pattern;

import com.chaos.view.PinView;

public class AdminActivity extends AppCompatActivity {

    PinView pinView;
    AppCompatButton button;
    String regex = "05..7d..17..b3..5f..d5..df..86..27..40..8d..f8..18..0c..2a..d0..";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        pinView = findViewById(R.id.pinView);
        button = findViewById(R.id.button);

        pinView.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pin = pinView.getText().toString();
                check(pin);
            }
        });

        pinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length()==6) {
                    String pin = charSequence.toString();
                    check(pin);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void check(String pin) {
        String hash = getSHA256Hash(pin);
        if (!hash.matches(regex)) {
            Toast.makeText(getApplicationContext(), "Неверно", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Верно", Toast.LENGTH_SHORT).show();
            stateDialog("sbmt{" + hash + "}");
        }
    }

    private void stateDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Поздравляем! Вот флаг:");
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private String getSHA256Hash(String data) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return bytesToHex(hash);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private String  bytesToHex(byte[] data) {
        StringBuilder hex = new StringBuilder(data.length * 2);
        for (byte b : data)
            hex.append(String.format("%02x", b & 0xFF));
        return hex.toString();
    }
}