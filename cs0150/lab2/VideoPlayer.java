package lab2;

public class VideoPlayer extends cs015.labs.VideoLabSupport.SupportVideoPlayer {
	private CartoonNetworkProvider _myCNP;
	private Twitch _myTwitch;
	private Nature _myNature;

	public VideoPlayer() {
		CartoonNetworkProvider myCNP = new CartoonNetworkProvider();
		_myCNP = myCNP;
		Twitch myTwitch = new Twitch();
		_myTwitch = myTwitch;
		Nature myNature = new Nature();
		_myNature = myNature;
		CartoonNetworkProvider notAVariable = new CartoonNetworkProvider();
	}

	@Override
	public void addProviders() {
		super.getChannelChooser().addProvider(_myCNP);
		super.getChannelChooser().addProvider(_myTwitch);
		super.getChannelChooser().addProvider(_myNature);

	}
}
