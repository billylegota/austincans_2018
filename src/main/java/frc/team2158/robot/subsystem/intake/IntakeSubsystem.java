package frc.team2158.robot.subsystem.intake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2158.robot.command.intake.StopIntake;

import java.util.logging.Logger;

public class IntakeSubsystem extends Subsystem {
    private static final Logger LOGGER = Logger.getLogger(IntakeSubsystem.class.getName());

    public enum IntakeDirection {IN, OUT, CLOCKWISE, COUNTERCLOCKWISE}
    public enum PivotDirection {UP, DOWN}

    private static final double DEFAULT_INTAKE_SPEED = 1.0;
    private static final double DEFAULT_PIVOT_SPEED = 0.75;

    private SpeedController leftSpeedController;
    private SpeedController rightSpeedController;
    private SpeedController pivotSpeedController;
    private DoubleSolenoid solenoid;

    public IntakeSubsystem(SpeedController leftSpeedController, SpeedController rightSpeedController,
                           SpeedController pivotSpeedController, DoubleSolenoid solenoid) {
        this.leftSpeedController = leftSpeedController;
        this.rightSpeedController = rightSpeedController;
        this.pivotSpeedController = pivotSpeedController;
        this.solenoid = solenoid;
        setDoubleSolenoidState(DoubleSolenoid.Value.kForward);
        LOGGER.info("Intake subsystem initialization complete!");
    }

    public void runIntake(IntakeDirection direction) {
        switch(direction) {
            case IN:
                leftSpeedController.set(-DEFAULT_INTAKE_SPEED);
                rightSpeedController.set(DEFAULT_INTAKE_SPEED);
                break;
            case OUT:
                leftSpeedController.set(DEFAULT_INTAKE_SPEED);
                rightSpeedController.set(-DEFAULT_INTAKE_SPEED);
                break;
            case CLOCKWISE:
                leftSpeedController.set(DEFAULT_INTAKE_SPEED);
                rightSpeedController.set(DEFAULT_INTAKE_SPEED);
                break;
            case COUNTERCLOCKWISE:
                leftSpeedController.set(-DEFAULT_INTAKE_SPEED);
                rightSpeedController.set(-DEFAULT_INTAKE_SPEED);
                break;
        }
    }

    public void stopIntake() {
        leftSpeedController.set(0.0);
        rightSpeedController.set(0.0);
    }

    public void pivotIntake(PivotDirection direction) {
        switch(direction) {
            case UP:
                pivotSpeedController.set(DEFAULT_PIVOT_SPEED);
                break;
            case DOWN:
                pivotSpeedController.set(-DEFAULT_PIVOT_SPEED);
                break;
        }
    }

    public void stopPivot() {
        pivotSpeedController.set(0.0);
    }

    public void setDoubleSolenoidState(DoubleSolenoid.Value state) {
        solenoid.set(state);
    }

    public void toggleSolenoidState() {
        switch(solenoid.get()) {
            case kForward:
                setDoubleSolenoidState(DoubleSolenoid.Value.kReverse);
                break;
            case kReverse:
                setDoubleSolenoidState(DoubleSolenoid.Value.kForward);
                break;
            case kOff:
                break;
        }
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new StopIntake());
    }
}
