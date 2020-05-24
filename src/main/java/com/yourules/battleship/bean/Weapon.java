package com.yourules.battleship.bean;

import com.yourules.battleship.bean.Constants.WeaponType;

public class Weapon {

	protected WeaponType type;
	protected int count;
	
	public Weapon(WeaponType type, int count) {
		super();
		this.type = type;
		this.count = count;
	}
	public WeaponType getType() {
		return type;
	}
	public void setType(WeaponType type) {
		this.type = type;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Weapon [type=" + type + ", count=" + count + "]";
	}
	public void increaseCount() {
		count++;
	}
	public void decreaseCount() {
		count--;
	}
}
