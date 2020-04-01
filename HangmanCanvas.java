/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;



public class HangmanCanvas extends GCanvas {

	private static final int WIDTH = 400;
	private static final int HEIGHT = 500;

	private int guessCtr = 1;
	private GLabel textDisplay = null;
	private String incGuessString = "";
	private GLabel incGuessLabel = null;

/** Resets the display so that only the scaffold appears */
	public void reset() {

		removeAll();
		buildScaffold();

	}

	public void buildScaffold()
	{
		GLine scaffold = new GLine(startX, startY, startX, startY - SCAFFOLD_HEIGHT );
		add(scaffold);
		GLine beam = new GLine(startX, startY - SCAFFOLD_HEIGHT, startX + BEAM_LENGTH, startY - SCAFFOLD_HEIGHT);
		add(beam);
		GLine rope = new GLine(startX + BEAM_LENGTH, startY - SCAFFOLD_HEIGHT, startX + BEAM_LENGTH, (startY - SCAFFOLD_HEIGHT) + ROPE_LENGTH);
		add(rope);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		if(textDisplay != null)
		{
			remove(textDisplay);
		}

		textDisplay = new GLabel(word,textStartX, textStartY );
		textDisplay.setFont("Times New Roman-40");
		add(textDisplay);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		if(incGuessLabel != null)
		{
			remove(incGuessLabel);
		}
		incGuessString += letter;
		incGuessLabel = new GLabel(incGuessString,textStartX, textStartY + 45);
		incGuessLabel.setFont("Times New Roman-40");
		add(incGuessLabel);

		buildHangMan();
		guessCtr ++ ;

	}

	public void buildHangMan()
	{
		switch(guessCtr)
		{
			case(1):
				GOval head = new GOval(startXHead, startYHead, HEAD_RADIUS*2, HEAD_RADIUS*2);
				add(head);
				break;
			case(2):
				GLine body = new GLine(startXBodyP1, startYBodyP1, startXBodyP2, startYBodyP2);
				add(body);
				break;
			case(3):
				GLine leftArm = new GLine(startXLeftArmP1, startYLeftArmP1, startXLeftArmP2, startYLeftArmP2);
				add(leftArm);
				GLine leftHand = new GLine(startXLeftHandP1, startYLeftHandP1, startXLeftHandP2, startYLeftHandP2);
				add(leftHand);
				break;

			case(4):
				GLine RightArm = new GLine(startXRightArmP1, startYRightArmP1, startXRightArmP2, startYRightArmP2);
				add(RightArm);
				GLine RightHand = new GLine(startXRightHandP1, startYRightHandP1, startXRightHandP2, startYRightHandP2);
				add(RightHand);
				break;
			case(5):
				GLine leftHip = new GLine(startXLeftHipP1, startYLeftHipP1, startXLefHipP2, startYLeftHipP2);
				add(leftHip);
				GLine leftLeg = new GLine(startXLeftLegP1, startYLeftLegP1, startXLeftLegP2, startYLeftLegP2);
				add(leftLeg);
				break;
			case(6):
				GLine RightHip = new GLine(startXRightHipP1, startYRightHipP1, startXRightHipP2, startYRightHipP2);
				add(RightHip);
				GLine RightLeg = new GLine(startXRightLegP1, startYRightLegP1, startXRightLegP2, startYRightLegP2);
				add(RightLeg);
				break;
			case(7):
				GLine leftFoot = new GLine(startXLeftFootP1, startYLeftFootP1, startXLeftFootP2, startYLeftFootP2);
				add(leftFoot);
				break;
			case(8):
				GLine rightFoot = new GLine(startXrightFootP1, startYrightFootP1, startXrightFootP2, startYrightFootP2);
				add(rightFoot);
				break;
		}
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

	private double textStartX = WIDTH/2 - BEAM_LENGTH;
	private double textStartY = HEIGHT/2 + SCAFFOLD_HEIGHT/2 + 20;

	private  double startX = WIDTH/2 - BEAM_LENGTH;
	private double startY = HEIGHT/2 + SCAFFOLD_HEIGHT/2;
	private double startXHead = (startX + BEAM_LENGTH) - HEAD_RADIUS;
	private double startYHead = (startY - SCAFFOLD_HEIGHT) + ROPE_LENGTH;

	private double startXBodyP1 = startX + BEAM_LENGTH;
	private double startYBodyP1 = (startY - SCAFFOLD_HEIGHT) + ROPE_LENGTH + (HEAD_RADIUS*2);
	private double startXBodyP2 = startX + BEAM_LENGTH;
	private double startYBodyP2 = (startY - SCAFFOLD_HEIGHT) + ROPE_LENGTH + + (HEAD_RADIUS*2) + BODY_LENGTH;

	private double startXLeftArmP1 = startX + BEAM_LENGTH;
	private double startYLeftArmP1 = (startY - SCAFFOLD_HEIGHT) + ROPE_LENGTH + (HEAD_RADIUS*2) + ARM_OFFSET_FROM_HEAD;
	private double startXLeftArmP2 = startX + BEAM_LENGTH + UPPER_ARM_LENGTH;
	private double startYLeftArmP2 = (startY - SCAFFOLD_HEIGHT) + ROPE_LENGTH + (HEAD_RADIUS*2) + ARM_OFFSET_FROM_HEAD;

	private double startXLeftHandP1 = startX + BEAM_LENGTH + UPPER_ARM_LENGTH;
	private double startYLeftHandP1 = (startY - SCAFFOLD_HEIGHT) + ROPE_LENGTH + (HEAD_RADIUS*2) + ARM_OFFSET_FROM_HEAD;
	private double startXLeftHandP2 = startX + BEAM_LENGTH + UPPER_ARM_LENGTH;
	private double startYLeftHandP2 = (startY - SCAFFOLD_HEIGHT) + ROPE_LENGTH + (HEAD_RADIUS*2) + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH;

	private double startXRightArmP1 = startX + BEAM_LENGTH;
	private double startYRightArmP1 = (startY - SCAFFOLD_HEIGHT) + ROPE_LENGTH + (HEAD_RADIUS*2) + ARM_OFFSET_FROM_HEAD;
	private double startXRightArmP2 = (startX + BEAM_LENGTH) - UPPER_ARM_LENGTH;
	private double startYRightArmP2 = (startY - SCAFFOLD_HEIGHT) + ROPE_LENGTH + (HEAD_RADIUS*2) + ARM_OFFSET_FROM_HEAD;

	private double startXRightHandP1 = (startX + BEAM_LENGTH) - UPPER_ARM_LENGTH;
	private double startYRightHandP1 = (startY - SCAFFOLD_HEIGHT) + ROPE_LENGTH + (HEAD_RADIUS*2) + ARM_OFFSET_FROM_HEAD;
	private double startXRightHandP2 = (startX + BEAM_LENGTH) - UPPER_ARM_LENGTH;
	private double startYRightHandP2 = (startY - SCAFFOLD_HEIGHT) + ROPE_LENGTH + (HEAD_RADIUS*2) + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH;

	private double startXLeftHipP1 = startX + BEAM_LENGTH;
	private double startYLeftHipP1 = (startY - SCAFFOLD_HEIGHT) + ROPE_LENGTH + + (HEAD_RADIUS*2) + BODY_LENGTH;
	private double startXLefHipP2 = startXLeftHipP1 + HIP_WIDTH;
	private double startYLeftHipP2 = startYLeftHipP1;

	private double startXLeftLegP1 = startXLefHipP2;
	private double startYLeftLegP1 = startYLeftHipP2;
	private double startXLeftLegP2 = startXLefHipP2;
	private double startYLeftLegP2 = startYLeftHipP2 + LEG_LENGTH;

	private double startXRightHipP1 = startX + BEAM_LENGTH;
	private double startYRightHipP1 = (startY - SCAFFOLD_HEIGHT) + ROPE_LENGTH + (HEAD_RADIUS*2) + BODY_LENGTH;
	private double startXRightHipP2 = startXRightHipP1 - HIP_WIDTH;
	private double startYRightHipP2 = startYRightHipP1;

	private double startXRightLegP1 = startXRightHipP2;
	private double startYRightLegP1 = startYRightHipP2;
	private double startXRightLegP2 = startXRightHipP2;
	private double startYRightLegP2 = startYRightHipP2 + LEG_LENGTH;

	private double startXLeftFootP1 = startXLeftLegP2;
	private double startYLeftFootP1 = startYLeftLegP2;
	private double startXLeftFootP2 = startXLeftLegP2 + FOOT_LENGTH;
	private double startYLeftFootP2 = startYLeftLegP2;

	private double startXrightFootP1 = startXRightLegP2;
	private double startYrightFootP1 = startYRightLegP2;
	private double startXrightFootP2 = startXRightLegP2 - FOOT_LENGTH;
	private double startYrightFootP2 = startYRightLegP2;

}
