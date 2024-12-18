package RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo;

public class AttemptResponse {
  private boolean correct;
  private double rewardAmount;
  private String correctOption;
  private String message;

  public AttemptResponse(boolean correct, double rewardAmount, String correctOption, String message) {
    this.correct = correct;
    this.rewardAmount = rewardAmount;
    this.correctOption = correctOption;
    this.message = message;
  }

  public boolean isCorrect() {
    return correct;
  }

  public void setCorrect(boolean correct) {
    this.correct = correct;
  }

  public double getRewardAmount() {
    return rewardAmount;
  }

  public void setRewardAmount(double rewardAmount) {
    this.rewardAmount = rewardAmount;
  }

  public String getCorrectOption() {
    return correctOption;
  }

  public void setCorrectOption(String correctOption) {
    this.correctOption = correctOption;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}