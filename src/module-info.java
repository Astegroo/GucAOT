module AttackOnTitans
{
	requires javafx.controls;
	requires javafx.fxml;
	requires junit;
	exports game.tests to junit;
	exports game.engine;
	exports game.GUI;

}