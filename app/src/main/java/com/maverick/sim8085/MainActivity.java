package com.maverick.sim8085;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

	private static int count = 0;

	private static char[] regB = new char[2];
	private static char[] regC = new char[2];
	private static char[] regE = new char[2];
	private static char[] regH = new char[2];
	private static char[] regL = new char[2];
	private static char[] accumulator = new char[2];
	private static char[] flagReg = new char[2];
	private static char[] programCounter = new char[4];
	private static char[] stackPointer = new char[4];

	private static char[][] memory = new char[28416][2]; // The user memory starts from 8100 to f000 which is 28416 Memory Locations of 8 bit size

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	private char getNum(ImageView digit){
		char num = '0';
		Drawable drawable = digit.getDrawable();
		Drawable[] digits = {
			getResources().getDrawable(R.drawable.ic__16segment0),
			getResources().getDrawable(R.drawable.ic__16segment1),
			getResources().getDrawable(R.drawable.ic__16segment2),
			getResources().getDrawable(R.drawable.ic__16segment3),
			getResources().getDrawable(R.drawable.ic__16segment4),
			getResources().getDrawable(R.drawable.ic__16segment5),
			getResources().getDrawable(R.drawable.ic__16segment6),
			getResources().getDrawable(R.drawable.ic__16segment7),
			getResources().getDrawable(R.drawable.ic__16segment8),
			getResources().getDrawable(R.drawable.ic__16segment9),
			getResources().getDrawable(R.drawable.ic__16segmenta),
			getResources().getDrawable(R.drawable.ic__16segmentb),
			getResources().getDrawable(R.drawable.ic__16segmentc),
			getResources().getDrawable(R.drawable.ic__16segmentd),
			getResources().getDrawable(R.drawable.ic__16segmente),
			getResources().getDrawable(R.drawable.ic__16segmentf)
		};
		if(drawable == digits[0]){
			num = '0';
		}
		else if(drawable == digits[1]){
			num = '1';
		}
		else if(drawable == digits[2]){
			num = '2';
		}
		else if(drawable == digits[3]){
			num = '3';
		}
		else if(drawable == digits[4]){
			num = '4';
		}
		else if(drawable == digits[5]){
			num = '5';
		}
		else if(drawable == digits[6]){
			num = '6';
		}
		else if(drawable == digits[7]){
			num = '7';
		}
		else if(drawable == digits[8]){
			num = '8';
		}
		else if(drawable == digits[9]){
			num = '9';
		}
		else if(drawable == digits[10]){
			num = 'a';
		}
		else if(drawable == digits[11]){
			num = 'b';
		}
		else if(drawable == digits[12]){
			num = 'c';
		}
		else if(drawable == digits[13]){
			num = 'd';
		}
		else if(drawable == digits[14]){
			num = 'e';
		}
		else if(drawable == digits[15]){
			num = 'f';
		}

		return num;
	}

	private char[] getAddress(View view){
		ImageView address1 = view.findViewById(R.id.ivAddr1);
		ImageView address2 = view.findViewById(R.id.ivAddr2);
		ImageView address3 = view.findViewById(R.id.ivAddr3);
		ImageView address4 = view.findViewById(R.id.ivAddr4);
		char[] address = {getNum(address1),getNum(address2),getNum(address3),getNum(address4)};
		return address;
	}

	private char[] getData(View view){
		ImageView data1 = view.findViewById(R.id.ivData1);
		ImageView data2 = view.findViewById(R.id.ivData2);
		char[] data = {getNum(data1),getNum(data2)};
		return data;
	}

	public void onShowMemoryClicked(View view){
		Intent intent = new Intent(this, MemoryActivity.class);
		startActivity(intent);
	}

	public void onNextClicked(View view){
		char[] address = getAddress(view);
		char[] data = getData(view);
		int[] addNum = new int[8];
		for(int i=0;i< address.length;i++){
			if(address[i] == 'a'){
				addNum[i] = 10;
			}
			else if(address[i] == 'b'){
				addNum[i] = 11;
			}
			else if(address[i] == 'c'){
				addNum[i] = 12;
			}
			else if(address[i] == 'd'){
				addNum[i] = 13;
			}
			else if(address[i] == 'e'){
				addNum[i] = 14;
			}
			else if(address[i] == 'f'){
				addNum[i] = 15;
			}
			else {
				addNum[i] = Character.getNumericValue(address[i]);
			}
		}
		int sum = 0;
		for(int i=0;i<addNum.length;i++){
			sum += addNum[i] * Math.pow(16,(addNum.length-i));
		}
		int index = 33024 - sum;
		memory[index][0] = data[0];
		memory[index][1] = data[1];
		index++;	}

	public void onPrevClicked(View view){
		char[] address = getAddress(view);
		char[] data = getData(view);
	}

	public void onExecuteClicked(View view){
		char[] address = getAddress(view);
		char[] data = getData(view);
		int[] addNum = new int[8];
		for(int i=0;i< address.length;i++){
			if(address[i] == 'a'){
				addNum[i] = 10;
			}
			else if(address[i] == 'b'){
				addNum[i] = 11;
			}
			else if(address[i] == 'c'){
				addNum[i] = 12;
			}
			else if(address[i] == 'd'){
				addNum[i] = 13;
			}
			else if(address[i] == 'e'){
				addNum[i] = 14;
			}
			else if(address[i] == 'f'){
				addNum[i] = 15;
			}
			else {
				addNum[i] = Character.getNumericValue(address[i]);
			}
		}
		int sum = 0;
		for(int i=0;i<addNum.length;i++){
			sum += addNum[i] * Math.pow(16,(addNum.length-i));
		}
		int index = 33024 - sum;
		memory[index][0] = data[0];
		memory[index][1] = data[1];
	}

	public void onFClicked(View view) {
		if(count == 0){
			ImageView imageView = findViewById(R.id.ivAddr1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentf));
			count++;
		}
		else if(count == 1){
			ImageView imageView = findViewById(R.id.ivAddr2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentf));
			count++;
		}
		else if(count == 2){
			ImageView imageView = findViewById(R.id.ivAddr3);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentf));
			count++;
		}
		else if(count == 3){
			ImageView imageView = findViewById(R.id.ivAddr4);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentf));
			count++;
		}
		else if(count == 4){
			ImageView imageView = findViewById(R.id.ivData1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentf));
			count++;
		}
		else if(count == 5){
			ImageView imageView = findViewById(R.id.ivData2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentf));
			count = 0;
		}
	}

	public void onEClicked(View view) {
		if(count == 0){
			ImageView imageView = findViewById(R.id.ivAddr1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmente));
			count++;
		}
		else if(count == 1){
			ImageView imageView = findViewById(R.id.ivAddr2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmente));
			count++;
		}
		else if(count == 2){
			ImageView imageView = findViewById(R.id.ivAddr3);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmente));
			count++;
		}
		else if(count == 3){
			ImageView imageView = findViewById(R.id.ivAddr4);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmente));
			count++;
		}
		else if(count == 4){
			ImageView imageView = findViewById(R.id.ivData1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmente));
			count++;
		}
		else if(count == 5){
			ImageView imageView = findViewById(R.id.ivData2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmente));
			count = 0;
		}
	}

	public void onDClicked(View view) {
		if(count == 0){
			ImageView imageView = findViewById(R.id.ivAddr1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentd));
			count++;
		}
		else if(count == 1){
			ImageView imageView = findViewById(R.id.ivAddr2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentd));
			count++;
		}
		else if(count == 2){
			ImageView imageView = findViewById(R.id.ivAddr3);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentd));
			count++;
		}
		else if(count == 3){
			ImageView imageView = findViewById(R.id.ivAddr4);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentd));
			count++;
		}
		else if(count == 4){
			ImageView imageView = findViewById(R.id.ivData1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentd));
			count++;
		}
		else if(count == 5){
			ImageView imageView = findViewById(R.id.ivData2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentd));
			count = 0;
		}
	}

	public void onCClicked(View view) {
		if(count == 0){
			ImageView imageView = findViewById(R.id.ivAddr1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentc));
			count++;
		}
		else if(count == 1){
			ImageView imageView = findViewById(R.id.ivAddr2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentc));
			count++;
		}
		else if(count == 2){
			ImageView imageView = findViewById(R.id.ivAddr3);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentc));
			count++;
		}
		else if(count == 3){
			ImageView imageView = findViewById(R.id.ivAddr4);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentc));
			count++;
		}
		else if(count == 4){
			ImageView imageView = findViewById(R.id.ivData1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentc));
			count++;
		}
		else if(count == 5){
			ImageView imageView = findViewById(R.id.ivData2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentc));
			count = 0;
		}
	}

	public void onBClicked(View view) {
		if(count == 0){
			ImageView imageView = findViewById(R.id.ivAddr1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentb));
			count++;
		}
		else if(count == 1){
			ImageView imageView = findViewById(R.id.ivAddr2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentb));
			count++;
		}
		else if(count == 2){
			ImageView imageView = findViewById(R.id.ivAddr3);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentb));
			count++;
		}
		else if(count == 3){
			ImageView imageView = findViewById(R.id.ivAddr4);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentb));
			count++;
		}
		else if(count == 4){
			ImageView imageView = findViewById(R.id.ivData1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentb));
			count++;
		}
		else if(count == 5){
			ImageView imageView = findViewById(R.id.ivData2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmentb));
			count = 0;
		}
	}

	public void onAClicked(View view) {
		if(count == 0){
			ImageView imageView = findViewById(R.id.ivAddr1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmenta));
			count++;
		}
		else if(count == 1){
			ImageView imageView = findViewById(R.id.ivAddr2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmenta));
			count++;
		}
		else if(count == 2){
			ImageView imageView = findViewById(R.id.ivAddr3);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmenta));
			count++;
		}
		else if(count == 3){
			ImageView imageView = findViewById(R.id.ivAddr4);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmenta));
			count++;
		}
		else if(count == 4){
			ImageView imageView = findViewById(R.id.ivData1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmenta));
			count++;
		}
		else if(count == 5){
			ImageView imageView = findViewById(R.id.ivData2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segmenta));
			count = 0;
		}
	}

	public void onNineClicked(View view) {
		if(count == 0){
			ImageView imageView = findViewById(R.id.ivAddr1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment9));
			count++;
		}
		else if(count == 1){
			ImageView imageView = findViewById(R.id.ivAddr2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment9));
			count++;
		}
		else if(count == 2){
			ImageView imageView = findViewById(R.id.ivAddr3);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment9));
			count++;
		}
		else if(count == 3){
			ImageView imageView = findViewById(R.id.ivAddr4);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment9));
			count++;
		}
		else if(count == 4){
			ImageView imageView = findViewById(R.id.ivData1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment9));
			count++;
		}
		else if(count == 5){
			ImageView imageView = findViewById(R.id.ivData2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment9));
			count = 0;
		}
	}

	public void onEightClicked(View view) {
		if(count == 0){
			ImageView imageView = findViewById(R.id.ivAddr1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment8));
			count++;
		}
		else if(count == 1){
			ImageView imageView = findViewById(R.id.ivAddr2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment8));
			count++;
		}
		else if(count == 2){
			ImageView imageView = findViewById(R.id.ivAddr3);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment8));
			count++;
		}
		else if(count == 3){
			ImageView imageView = findViewById(R.id.ivAddr4);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment8));
			count++;
		}
		else if(count == 4){
			ImageView imageView = findViewById(R.id.ivData1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment8));
			count++;
		}
		else if(count == 5){
			ImageView imageView = findViewById(R.id.ivData2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment8));
			count = 0;
		}
	}

	public void onSevenClicked(View view) {
		if(count == 0){
			ImageView imageView = findViewById(R.id.ivAddr1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment7));
			count++;
		}
		else if(count == 1){
			ImageView imageView = findViewById(R.id.ivAddr2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment7));
			count++;
		}
		else if(count == 2){
			ImageView imageView = findViewById(R.id.ivAddr3);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment7));
			count++;
		}
		else if(count == 3){
			ImageView imageView = findViewById(R.id.ivAddr4);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment7));
			count++;
		}
		else if(count == 4){
			ImageView imageView = findViewById(R.id.ivData1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment7));
			count++;
		}
		else if(count == 5){
			ImageView imageView = findViewById(R.id.ivData2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment7));
			count = 0;
		}
	}

	public void onSixClicked(View view) {
		if(count == 0){
			ImageView imageView = findViewById(R.id.ivAddr1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment6));
			count++;
		}
		else if(count == 1){
			ImageView imageView = findViewById(R.id.ivAddr2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment6));
			count++;
		}
		else if(count == 2){
			ImageView imageView = findViewById(R.id.ivAddr3);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment6));
			count++;
		}
		else if(count == 3){
			ImageView imageView = findViewById(R.id.ivAddr4);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment6));
			count++;
		}
		else if(count == 4){
			ImageView imageView = findViewById(R.id.ivData1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment6));
			count++;
		}
		else if(count == 5){
			ImageView imageView = findViewById(R.id.ivData2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment6));
			count = 0;
		}
	}

	public void onFiveClicked(View view) {
		if(count == 0){
			ImageView imageView = findViewById(R.id.ivAddr1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment5));
			count++;
		}
		else if(count == 1){
			ImageView imageView = findViewById(R.id.ivAddr2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment5));
			count++;
		}
		else if(count == 2){
			ImageView imageView = findViewById(R.id.ivAddr3);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment5));
			count++;
		}
		else if(count == 3){
			ImageView imageView = findViewById(R.id.ivAddr4);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment5));
			count++;
		}
		else if(count == 4){
			ImageView imageView = findViewById(R.id.ivData1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment5));
			count++;
		}
		else if(count == 5){
			ImageView imageView = findViewById(R.id.ivData2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment5));
			count = 0;
		}
	}

	public void onFourClicked(View view) {
		if(count == 0){
			ImageView imageView = findViewById(R.id.ivAddr1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment4));
			count++;
		}
		else if(count == 1){
			ImageView imageView = findViewById(R.id.ivAddr2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment4));
			count++;
		}
		else if(count == 2){
			ImageView imageView = findViewById(R.id.ivAddr3);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment4));
			count++;
		}
		else if(count == 3){
			ImageView imageView = findViewById(R.id.ivAddr4);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment4));
			count++;
		}
		else if(count == 4){
			ImageView imageView = findViewById(R.id.ivData1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment4));
			count++;
		}
		else if(count == 5){
			ImageView imageView = findViewById(R.id.ivData2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment4));
			count = 0;
		}
	}

	public void onThreeClicked(View view) {
		if(count == 0){
			ImageView imageView = findViewById(R.id.ivAddr1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment3));
			count++;
		}
		else if(count == 1){
			ImageView imageView = findViewById(R.id.ivAddr2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment3));
			count++;
		}
		else if(count == 2){
			ImageView imageView = findViewById(R.id.ivAddr3);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment3));
			count++;
		}
		else if(count == 3){
			ImageView imageView = findViewById(R.id.ivAddr4);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment3));
			count++;
		}
		else if(count == 4){
			ImageView imageView = findViewById(R.id.ivData1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment3));
			count++;
		}
		else if(count == 5){
			ImageView imageView = findViewById(R.id.ivData2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment3));
			count = 0;
		}
	}

	public void onTwoClicked(View view) {
		if(count == 0){
			ImageView imageView = findViewById(R.id.ivAddr1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment2));
			count++;
		}
		else if(count == 1){
			ImageView imageView = findViewById(R.id.ivAddr2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment2));
			count++;
		}
		else if(count == 2){
			ImageView imageView = findViewById(R.id.ivAddr3);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment2));
			count++;
		}
		else if(count == 3){
			ImageView imageView = findViewById(R.id.ivAddr4);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment2));
			count++;
		}
		else if(count == 4){
			ImageView imageView = findViewById(R.id.ivData1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment2));
			count++;
		}
		else if(count == 5){
			ImageView imageView = findViewById(R.id.ivData2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment2));
			count = 0;
		}
	}

	public void onOneClicked(View view) {
		if(count == 0){
			ImageView imageView = findViewById(R.id.ivAddr1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment1));
			count++;
		}
		else if(count == 1){
			ImageView imageView = findViewById(R.id.ivAddr2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment1));
			count++;
		}
		else if(count == 2){
			ImageView imageView = findViewById(R.id.ivAddr3);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment1));
			count++;
		}
		else if(count == 3){
			ImageView imageView = findViewById(R.id.ivAddr4);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment1));
			count++;
		}
		else if(count == 4){
			ImageView imageView = findViewById(R.id.ivData1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment1));
			count++;
		}
		else if(count == 5){
			ImageView imageView = findViewById(R.id.ivData2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment1));
			count = 0;
		}
	}

	public void onZeroClicked(View view) {
		if(count == 0){
			ImageView imageView = findViewById(R.id.ivAddr1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment0));
			count++;
		}
		else if(count == 1){
			ImageView imageView = findViewById(R.id.ivAddr2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment0));
			count++;
		}
		else if(count == 2){
			ImageView imageView = findViewById(R.id.ivAddr3);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment0));
			count++;
		}
		else if(count == 3){
			ImageView imageView = findViewById(R.id.ivAddr4);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment0));
			count++;
		}
		else if(count == 4){
			ImageView imageView = findViewById(R.id.ivData1);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment0));
			count++;
		}
		else if(count == 5){
			ImageView imageView = findViewById(R.id.ivData2);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic__16segment0));
			count = 0;
		}
	}
}