# "Miliano-eWallet" Back-End Solution:

## Overview:
This is a fintech application that is a rebuild of a legacy system. 
The application is a digital wallet that allows users to store, send, and receive money.  
The application is expected to support about 1 million customers around the world


## Initial Assessment and Planning:

We will Conduct a comprehensive assessment of the current back-end architecture, identifying pain points, scalability issues, and areas for improvement.  
having completed that we will liaise with the business team to understand the requirements, prioritize features, and define the success criteria.  
We will also establish the key performance indicators (KPIs) to measure the effectiveness of the new back-end solution.  
having completed this we will then move to the technology stack

### Technology Stack Selection:

The project owner together with the scrum master will research and evaluate modern back-end technologies and frameworks suitable for   
high scalability and performance.  
they will factor such as language versatility, scalability, community support, and integration capabilities.  
for the purposes of this assesment test we have chosen kotlin with spring framework as it's a modern technology stack   
that aligns with the long-term goals of the company and supports the anticipated growth of the customer base.  
Next we will move to the architecture design


### Architecture Design:

For the archtectural design the scrum master and project manager will design a modular and scalable architecture   
that can accommodate current needs and future expansion. We will use a microservice architecture which would ensure that   
each module handles one responsibility whiles staying decoupled from the other modules. 
````
The microservice proposal are 
1. Core Microservice: responsible for user authentication, authorization, and profile management. It will also handle the creation of transactions and updating of wallets
2. Transaction Microservice: responsible for processing transactions. This would most likely integrate with some third party payment gateway and return to our core microservice the status of transactions. 
3. Fraud Detection Microservice: responsible for detecting fraudulent activities and flagging suspicious transactions. This would be a standalone service that would be called by the core microservice.
4. Notification Microservice: responsible for sending notifications to users via email, SMS, or push notifications. This would be a standalone service that would be called by the core microservice.
````

Next we will move to the team alignment and roles assignment and project commencement

### Team Alignment and Roles Assignment and Project Commencement:

The scrum master will collaborate with the tech team to assign roles and responsibilities based on expertise and experience.  
The scrum master will then appoint a team lead for the back-end development responsible for coordinating tasks,  
resolving issues, and ensuring adherence to best practices.

````
The following steps will be taken from the commence the project to the end of the project:
1. Agile Development Approach: Implement Agile methodologies such as Scrum or Kanban for iterative development and continuous delivery. We will conduct regular sprint planning, review, and retrospective meetings to track progress, gather feedback, and make adjustments as needed. We will utilize tools like Jira or Trello for project management and tracking of tasks and user stories.
2. Continuous Integration and Deployment (CI/CD): we will set up automated CI/CD pipelines to streamline the build, test, and deployment processes. we will integrate automated testing suites for unit tests, integration tests, and end-to-end tests to maintain code quality and stability. we will ensure that deployments are automated and can be rolled back easily in case of issues.
3. Security and Compliance: we will implement robust security measures to protect customer data and prevent unauthorized access or breaches. we will adhere to industry standards and regulations such as GDPR, PCI-DSS, and SOC 2 compliance. we will conduct regular security audits and penetration testing to identify and address vulnerabilities.
4. Monitoring and Performance Optimization: we will set up comprehensive monitoring and logging infrastructure to track system performance, identify bottlenecks, and troubleshoot issues proactively. we will utilize tools like Prometheus, Grafana, and ELK stack for real-time monitoring and analysis. we will continuously optimize the back-end system for performance, scalability, and cost-efficiency based on monitoring data and user feedback.
5. Documentation and Knowledge Transfer: we will document the architecture, design decisions, and implementation details comprehensively to ensure knowledge transfer and facilitate onboarding of new team members. we will conduct knowledge-sharing sessions and workshops within the team to disseminate best practices, lessons learned, and new technologies.
6. Pilot Deployment and Iterative Improvement: we will deploy the pilot system to a limited user base or environment for testing and validation. we will gather feedback from users, stakeholders, and performance metrics to identify areas for improvement. we will iterate on the solution based on feedback, addressing any issues or enhancements identified during the pilot phase. we will gradually scale up the deployment to larger user bases and environments, ensuring stability and performance at each stage.
7. Post-Deployment Support and Maintenance: we will provide post-deployment support to address any issues, bugs, or feature requests that arise after the initial launch. we will monitor system performance and user feedback to identify areas for further improvement and optimization. we will conduct regular maintenance activities such as software updates, security patches, and database backups to ensure the system's reliability and availability.
````

## Running the application 

To run the application, you need to have the following installed on your machine:
1. Docker

To run the application, follow these steps:
1. Clone the repository to your local machine
2. Navigate to the root directory of the project
3. Run the following command to run the application:
```
docker-compose up
```

The application will start running on `http://localhost:8081`

## API Documentation

The API documentation can be found at `http://localhost:8081/swagger-ui.html`
