package frc.team2158.robot.subsystem.lift;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2158.robot.command.lift.StopLift;

import java.util.logging.Logger;

public class LiftSubsystem extends Subsystem {
    private static final Logger LOGGER = Logger.getLogger(LiftSubsystem.class.getName());

    public enum Direction {UP, DOWN}

    private SpeedController liftSpeedController;

    public LiftSubsystem(SpeedController liftSpeedController) {
        this.liftSpeedController = liftSpeedController;
        LOGGER.info("Lift subsystem initialization complete!");
    }

    public void moveLift(Direction direction, double speed) {
        switch(direction) {
            case UP:
                liftSpeedController.set(speed);
                break;
            case DOWN:
                liftSpeedController.set(-speed);
                break;
        }
    }

    public void stopLift() {
        liftSpeedController.set(0.0);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new StopLift());
    }
}
