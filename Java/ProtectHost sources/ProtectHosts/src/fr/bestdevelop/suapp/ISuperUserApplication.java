package fr.bestdevelop.suapp;

public interface ISuperUserApplication extends ISuperUserDetector, ISudo {

	int run(String[] args);

}
