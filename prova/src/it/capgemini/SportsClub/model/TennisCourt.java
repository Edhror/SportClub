package it.capgemini.SportsClub.model;

public class TennisCourt extends Court{

	public TennisCourt(int id, String name, Surface surface, int numPlayers, double cost) {
		super(id, name, surface, numPlayers, cost);
		
	}

	@Override
	protected Surface[] allowedSurfaces() {
		return new Surface[] {Surface.GRASS, Surface.CLAY, Surface.HARDCOURT};
	}

	@Override
	protected int maxPlayers() {
		return 4;
	}

}
