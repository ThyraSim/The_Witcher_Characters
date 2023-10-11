package com.example.thewitcher.contracts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thewitcher.creationSelection.ChooseWeaponActivity;

public class WeaponResultContract extends ActivityResultContract<Integer, Integer> {
    @NonNull
    @Override
    public Intent createIntent(@NonNull Context context, Integer input) {
        Intent intent = new Intent(context, ChooseWeaponActivity.class);
        return intent;
    }

    @Override
    public Integer parseResult(int resultCode, @Nullable Intent intent) {
        if (resultCode == Activity.RESULT_OK && intent != null) {
            return intent.getIntExtra("selected_weapon_id", -1);
        }
        return null;
    }
}