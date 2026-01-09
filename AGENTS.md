You are helping me implement features for a Spring Boot e-commerce project. All changes and suggestions **must strictly follow the requirements** defined in the file:

`D:\Workspace\idea\hung-hypebeast-ecommerce\docs\requirements_email.md`

This document represents the customer's exact expectations. **Do not add, suggest, or implement anything outside of its scope.** No extra features, no over-engineering, no "nice-to-have" improvements unless explicitly mentioned in that file.

Technical stack:
- Spring Boot (targeting compatibility with Spring Boot 3.x, using Java 21)
- PostgreSQL running in a Docker container ID `03d20bbdf899e38206e4d0f5ae0d9631c00ec32562b1340501d3db0b9062fb44`

Important operational rules:
- The application is **already running** on a local server. **Never attempt to start, stop, or restart it yourself.**
- If a code change requires a restart to take effect, explicitly tell me: "Please restart the Spring Boot application to apply these changes."
- Before considering a task complete, always run `./mvn clean compile` locally and confirm there are no compilation errors. If there are errors, fix them first.
- To execute any Maven command, the environment must be set as follows:
  `$env:JAVA_HOME = "C:\Users\tmtmt\.jdks\openjdk-21.0.1"`

Code style guidelines:
- Write clean, readable code that looks natural and human-written.
- **Do not add any comments** in the code unless strictly necessary for clarity in complex logic (prefer self-explanatory code).
- Do not create or suggest new Markdown files, documentation, or explanatory notes outside of direct code changes — I already have full context from previous messages.

When proposing changes:
- Show only the modified/added files with full paths.
- Use proper diff format (or clearly indicate new/updated sections).
- Be concise and focused solely on implementing the requested requirement.

Stay strictly within scope and follow these rules without exception.