package frc.robot.Subsystems;

import frc.robot.Constants;
import frc.robot.Hardware;
import frc.robot.Lib.SubsystemFramework;
import frc.robot.Util.doubleSolenoid;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class Drivetrain extends SubsystemFramework {
    //Hardware

    private DifferentialDrive myRobot;
	private WPI_VictorSPX rightMasterMotor;
	private WPI_VictorSPX leftMasterMotor;
	private WPI_VictorSPX rightSlaveMotor;
	private WPI_VictorSPX leftSlaveMotor;
	private doubleSolenoid shifter;

	// State
	public DrivetrainStates state = DrivetrainStates.HighGear;

	public Drivetrain(WPI_VictorSPX rightMasterMotor, WPI_VictorSPX rightSlaveMotor,
			WPI_VictorSPX leftMasterMotor, WPI_VictorSPX leftSlaveMotor, 
			doubleSolenoid shifter) {
	

				// Setup Drivetrain
		myRobot = new DifferentialDrive(rightMasterMotor, leftMasterMotor);

		// Motor Controllers
		this.rightMasterMotor = rightMasterMotor;
		this.rightSlaveMotor = rightSlaveMotor;
		this.leftMasterMotor = leftMasterMotor;
		this.leftSlaveMotor = leftSlaveMotor;

		// Shifter
		this.shifter = shifter;

	}

	public enum DrivetrainStates {
		LowGear, HighGear;
	}


	@Override
	public void update() {

        DrivetrainStates newState = state;

		switch (state) {
			case LowGear:
				
				// Shift Into LowGear
				shifter.close();
	
				// Joystick Control
				myRobot.tankDrive((Hardware.driverPad.getRawAxis(1) * -1), (Hardware.driverPad.getRawAxis(4) * -1), false);
	
				// Switch To HighGear When Asked
				if (Hardware.driverPad.getRawButton(Constants.DRIVETRAIN_HIGHGEAR)) {
					newState = DrivetrainStates.HighGear;
				
				}
	break;		
			case HighGear:
				// Shift Into HighGear
				shifter.open();
	
				// Joystick Control
				myRobot.tankDrive((Hardware.driverPad.getRawAxis(1) * -1), (Hardware.driverPad.getRawAxis(4) * -1),
						false);
	
				// Switch To LowGear When Asked
				if (Hardware.driverPad.getRawButton(Constants.DRIVETRAIN_LOWGEAR)) {
					newState = DrivetrainStates.LowGear;
				
				}
				
	break;
			
			default:
				newState = state;
			break;
		}

		if (newState != state) {
			state = newState;
		}

	}

	@Override
	public void outputToSmartDashboard() {

	}
	
	@Override
	public void setupSensors() {
				// Setup Master Slave Relationship
				rightSlaveMotor.follow(rightMasterMotor);
				leftSlaveMotor.follow(leftMasterMotor);
						
				// Setup Master Encoders
				rightMasterMotor.setSensorPhase(true);
				leftMasterMotor.setSensorPhase(true);
						
				// This initializes where the Encoder values come from
				rightMasterMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
				leftMasterMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		
				// This sets Encoder values to 0 when robot begins
				rightMasterMotor.setSelectedSensorPosition(0, 0, 10);
				leftMasterMotor.setSelectedSensorPosition(0, 0, 10);
				
				// Configures the Speed of Each motor
				rightMasterMotor.configNominalOutputForward(0, 10);
				leftMasterMotor.configNominalOutputForward(0, 10);
				rightMasterMotor.configNominalOutputReverse(0, 10);
				leftMasterMotor.configNominalOutputReverse(0, 10);
				rightMasterMotor.configPeakOutputForward(0.9, 10);
				leftMasterMotor.configPeakOutputForward(0.9, 10);
				rightMasterMotor.configPeakOutputReverse(-0.9, 10);
				leftMasterMotor.configPeakOutputReverse(-0.9, 10);
    }
} 