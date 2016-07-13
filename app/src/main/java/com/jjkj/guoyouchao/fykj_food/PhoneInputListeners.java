package com.jjkj.guoyouchao.fykj_food;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by guoyouchao on 16/6/20.
 */
public interface PhoneInputListeners extends TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after);

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) ;
//    {
//        Log.d("输入文本 = ",s.toString());
//        if (activity.phoneNumber.getText().length() == 11){
//            activity.loginButton.setBackgroundColor(Color.GREEN);
//            activity.canlogin = true;
//        }else{
//            activity.loginButton.setBackgroundColor(Color.LTGRAY);
//            activity.canlogin = false;
//        }
//    }

    @Override
    public void afterTextChanged(Editable s);
//    {

        /** 得到光标开始和结束位置 ,超过最大数后记录刚超出的数字索引进行控制 */
//        editStart = mEditTextMsg.getSelectionStart();
//        editEnd = mEditTextMsg.getSelectionEnd();
//        if (temp.length() > charMaxNum) {
//            s.delete(editStart - 1, editEnd);
//            int tempSelection = editStart;
//            mEditTextMsg.setText(s);
//            mEditTextMsg.setSelection(tempSelection);
//        }

//    }
}
