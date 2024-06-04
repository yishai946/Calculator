package il.ac.tcb.st.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn_calculate;
    TextView tv_result;
    Spinner sp_action;
    EditText et_num1;
    EditText et_num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_calculate = findViewById(R.id.btn_calculate);
        tv_result = findViewById(R.id.tv_result);
        sp_action = findViewById(R.id.sp_action);
        et_num1 = findViewById(R.id.et_num1);
        et_num2 = findViewById(R.id.et_num2);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.actions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_action.setAdapter(adapter);

        btn_calculate.setOnClickListener(onClickListenerCalculate);
    }

    View.OnClickListener onClickListenerCalculate = v -> {
        try {
            int num1 = Integer.parseInt(et_num1.getText().toString());
            int num2 = Integer.parseInt(et_num2.getText().toString());
            int res = 0;
            String action = sp_action.getSelectedItem().toString();

            switch (action){
                case "+":
                    res = num1 + num2;
                    break;
                case "-":
                    res = num1 - num2;
                    break;
                case "*":
                    res = num1 * num2;
                    break;
                case "/":
                    if(num2 == 0) throw new Exception("can't divide with 0");
                    res = num1 / num2;
                    break;
                case "^":
                    res = (int)Math.pow(num1, num2);
            }
            tv_result.setText(String.valueOf(res));
        }
        catch (Exception e){
            tv_result.setText("Error " + e.getMessage());
        }
    };

}