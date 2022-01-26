
// Arduino code generated from a model
// Copyright Team-E all right reserved


const int debounceDelay = 50; //Delay for button debounce
unsigned long debounceTime; //Timer for button debounce
unsigned long whileTimer; //Timer used in while loops into the state machine

enum ENUM_STATE { //States (into an enum) of the state machine
	state1, 
	state2
};

ENUM_STATE state; //Current state of the state machine

const int button0 = 9;
int button0State; //Current state of the Sensor (current reading)

const int button1 = 10;
int button1State; //Current state of the Sensor (current reading)

const int led0 = 12;


void setup(){
	debounceTime = 0; //Obvious init
	whileTimer = 0; //Obvious Init

	pinMode(button0, INPUT); // [Sensor, PIN : 9]
	pinMode(button1, INPUT); // [Sensor, PIN : 10]
	pinMode(led0, OUTPUT); // [Actuator, PIN : 12]

	state = state1; // Default state when entering the state-machine
}


void loop(){
	if ((millis() - debounceTime) > debounceDelay) { //Debounce Statement

		debounceTime = millis(); //Update debounce time

		switch(state) {

			case state1:
				digitalWrite(led0,LOW);
				whileTimer = millis();
				while(1) {
					button0State = digitalRead(button0);
					if( button0State == HIGH) {
						state = state2;
						break;
					}
				}
				break;

			case state2:
				digitalWrite(led0,HIGH);
				whileTimer = millis();
				while(1) {
					button1State = digitalRead(button1);
					if( button1State == HIGH) {
						state = state1;
						break;
					}
					if ((millis() - whileTimer) > 5000) {;
						state = state1;
						break;
					}
				}
				break;
		} //End of State machine
		delay(250); //Delay for hardware synchronization
	} //End of Debounce Statement
} //End of loop() function