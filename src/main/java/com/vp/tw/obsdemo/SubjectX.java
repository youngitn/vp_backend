package com.vp.tw.obsdemo;

import java.util.ArrayList;
import java.util.List;

public class SubjectX implements Subject {

	List<Observer> ObsList = new ArrayList<Observer>();
	@Override
	public void notifyObserves(String ytname) {
		for (Observer observer : ObsList) {
			observer.notifyx(ytname);
		}

	}

	@Override
	public void addObserves(Observer obs) {
		ObsList.add(obs);

	}

	@Override
	public void delObserves() {
		// TODO Auto-generated method stub

	}

}
