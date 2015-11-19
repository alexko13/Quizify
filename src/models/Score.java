package models;

public class Score {
	private int correct;
	private int total;
	
	public Score(int correct, int total) {
		this.correct = correct;
		this.total = total;
	}

	public int getCorrect() {
		return correct;
	}

	public int getTotal() {
		return total;
	}

	public double getPercentCorrect() {
		return ((double) correct) / total * 100;
	}

}
