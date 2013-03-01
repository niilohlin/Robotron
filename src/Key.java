
public class Key {
	// A little hack think of this as a C struct or a python namedtuple
	public Key(){}
	public Key(KeyType type, int key)
	{
		this.type = type;
		this.key = key;
	}
	public Key(KeyType type, int key, Direction direction)
	{
		this.type = type;
		this.key = key;
		this.direction = direction;
	}
	public KeyType type;
	public Direction direction = Direction.NULL;
	public int key;
	public boolean pressed = false;

}
