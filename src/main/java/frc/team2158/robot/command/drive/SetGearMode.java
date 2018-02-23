package frc.team2158.robot.command.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2158.robot.Robot;
import frc.team2158.robot.subsystem.drive.GearMode;

import java.util.logging.Logger;

public class SetGearMode extends Command {
    private static final Logger LOGGER = Logger.getLogger(SetGearMode.class.getName());

    private GearMode gearMode;

    public SetGearMode(GearMode gearMode) {
        this.gearMode = gearMode;
    }

    @Override
    protected void initialize() {
        Robot.getDriveSubsystem().setGearMode(gearMode);
        LOGGER.info(String.format("Set the gear mode to %s.", gearMode.name()));
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
