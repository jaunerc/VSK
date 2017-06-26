package ch.windmill.hslu.vsk.gof.adapter;

/**
 * Dummy implementation of an adapter.
 */
public class PowerAdapter implements PowerPlugin {

	private EuroPlug euroPlug;
	
	@Override
	public void pluginSwiss() {
		euroPlug.euroPlugin();
	}
}
