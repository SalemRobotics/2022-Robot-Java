package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class IndexHalt extends CommandBase {
    private final Indexer indexer;
    public IndexHalt(Indexer subsystem) {
        indexer = subsystem;
        addRequirements(indexer);
    }

    @Override
    public void execute() {
        indexer.halt();
    }
}
