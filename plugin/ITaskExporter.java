package plugin;

public interface ITaskExporter {
    /*
    subclass will override with necessary logic to export data.
    */
    public void export(String filename);
}
