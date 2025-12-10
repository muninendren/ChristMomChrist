# Christmas Mom-Child Assignment API Documentation

## Base URL
`/christmomchrist`

## Endpoints

### 1. Get Unregistered Users
**GET** `/unregisteredUsers`
- **Description**: Retrieves list of users who haven't registered yet
- **Response**: Array of user names (strings)
- **Example**: `["John", "Mary", "David"]`

### 2. Get Registered Users  
**GET** `/registeredUsers`
- **Description**: Retrieves list of users who have registered
- **Response**: Array of user names (strings)
- **Example**: `["Alice", "Bob", "Carol"]`

### 3. Register User
**POST** `/register`
- **Description**: Registers a new user in the system
- **Request Body**: User object
- **Response**: No content (void)
- **Example Request**:
```json
{
  "name": "John Doe",
  "email": "john@example.com"
}
```

### 4. Get Child Assignment
**GET** `/getChild`
- **Description**: Gets the child assigned to a specific user
- **Request Body**: User object
- **Response**: Assignment object containing child details
- **Example Request**:
```json
{
  "name": "Alice",
  "email": "alice@example.com"
}
```

### 5. Create Assignments
**POST** `/Assign`
- **Description**: Automatically assigns children to registered users
- **Request Body**: None
- **Response**: No content (void)
- **Note**: This endpoint triggers the assignment algorithm

## Usage Flow
1. Users register using `/register`
2. Admin triggers assignments using `/Assign`
3. Users check their assigned child using `/getChild`
4. Monitor registered/unregistered users with respective GET endpoints