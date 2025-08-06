# AI-task
To run application use the command: mvn spring-boot:run
To check for test coverage use the command: mvn clean test jacoco:report

# endpoints
    http://localhost:8080/users
    Method: POST
    Description: Allows new user to create an account
    Payload: {
        "username": "your-username"
    }
    Response: {
        "id": 3,
        "username": "Jack.Doe",
        "followers": 0,
        "following": 0
    }

    http://localhost:8080/users/{id}/follow/{followId}
    Description: Allows user to follow another user
    Payload:
        id: number,
        followId: number
    
    http://localhost:8080/posts
    Method: POST
    Description: Allows user to create a post
    Payload: {
        "title": "Some title",
        "body": "Some body",
        "authorId": "authorId"
    }
    Response: {
        "id": 1,
        "title": "Some title",
        "body": "Some body",
        "authorUsername": "John.Doe",
        "likes": 0
    }
    http://localhost:8080/posts
    Method: GET
    Description: Allows user to check for posts
    Response: [
        {
            "id": 1,
            "title": "Some title",
            "body": "Some body",
            "authorUsername": "John.Doe",
            "likes": 1
        },
        {
            "id": 2,
            "title": "Some title",
            "body": "Some body",
            "authorUsername": "John.Doe",
            "likes": 0
        },
        {
            "id": 3,
            "title": "Some title",
            "body": "Some body",
            "authorUsername": "John.Doe",
            "likes": 0
        },
        {
            "id": 4,
            "title": "Some title",
            "body": "Some body",
            "authorUsername": "John.Doe",
            "likes": 0
        }
    ]

    http://localhost:8080/{postId}/like/{userId}
    Description: Allows user to like a post
    Payload:
        postId: number,
        userId: number

# feedback
- Was it easy to complete the task using AI?
    Yes, it was fairly easy

 - How long did task take you to complete? (Please be honest, we need it to gather anonymized statistics)
    The task took me about 2 hours to complete

 - Was the code ready to run after generation? What did you have to change to make it usable?
    The code wasn't ready to run after the first generation, you can see the thrown exception in the 
    chat.log file

- Which challenges did you face during completion of the task?
    Didn't face much challenge apart from the thrown exception in one of the methods and an issue in 1 of the unit I had to modify the setup to make it work.

- Which specific prompts you learned as a good practice to complete the task?
    The prompt where I asked AI to generate unit tests, I should've mentioned that in the first prompt 
    as a requirement.
