package frc.team2158.robot.command.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2158.robot.Robot;
import frc.team2158.robot.subsystem.intake.IntakeSubsystem;

public class ToggleIntakeSolenoid extends Command {
    private IntakeSubsystem intakeSubsystem;

    public ToggleIntakeSolenoid() {
        this.intakeSubsystem = Robot.getIntakeSubsystem();
    }

    @Override
    protected void execute() {
        intakeSubsystem.toggleSolenoidState();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
