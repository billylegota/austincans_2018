package frc.team2158.robot.subsystem.drive;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import java.util.logging.Logger;

public class DriveSubsystem extends Subsystem {
    private static final Logger LOGGER = Logger.getLogger(DriveSubsystem.class.getName());

    private DifferentialDrive differentialDrive;
    private GearMode gearMode;
    private DoubleSolenoid gearboxSolenoid;

    public DriveSubsystem(SpeedController leftSpeedController, SpeedController rightSpeedController,
                          DoubleSolenoid gearboxSolenoid) {
        this.differentialDrive = new DifferentialDrive(leftSpeedController, rightSpeedController);
        differentialDrive.setSafetyEnabled(false);
        this.gearboxSolenoid = gearboxSolenoid;
        setGearMode(GearMode.LOW);
        LOGGER.info("Drive subsystem initialization complete!");
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        differentialDrive.tankDrive(leftSpeed, rightSpeed);
    }

    public void arcadeDrive(double velocity, double heading) {
        differentialDrive.arcadeDrive(velocity, heading);
    }

    public GearMode getGearMode() {
        return gearMode;
    }

    public void setGearMode(GearMode gearMode) {
        this.gearMode = gearMode;
        updateGearMode();
    }

    public void toggleGearMode() {
        switch(gearMode) {
            case HIGH:
                gearMode = GearMode.LOW;
                break;
            case LOW:
                gearMode = GearMode.HIGH;
                break;
        }
        updateGearMode();
    }

    private void updateGearMode() {
        switch(gearMode) {
            case HIGH:
                gearboxSolenoid.set(DoubleSolenoid.Value.kForward);
                break;
            case LOW:
                gearboxSolenoid.set(DoubleSolenoid.Value.kReverse);
                break;
        }
    }

    @Override
    protected void initDefaultCommand() {
        // TODO: Put a default command here.
    }
}
