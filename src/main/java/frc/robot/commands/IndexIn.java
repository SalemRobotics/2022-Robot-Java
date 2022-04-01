package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class IndexIn extends CommandBase {
    private final Indexer indexer;
    private final double speed;
    public IndexIn(Indexer subsystem, double speed) {
        indexer = subsystem;
        this.speed = speed;
        addRequirements(indexer);
    }

    @Override
    public void execute() {
        indexer.indexIn(speed);
    }

    @Override
    public void end(boolean interrupted) {
        indexer.halt();
    }
}
