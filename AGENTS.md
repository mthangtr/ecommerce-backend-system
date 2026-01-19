You are helping me implement features for a Spring Boot e-commerce project. All changes and suggestions **must strictly follow the requirements** defined in the file:

`D:\Workspace\idea\hung-hypebeast-ecommerce\docs\requirements_email.md`

This document represents the customer's exact expectations. **Do not add, suggest, or implement anything outside of its scope.** No extra features, no over-engineering, no "nice-to-have" improvements unless explicitly mentioned in that file.

Technical stack:
- Spring Boot (targeting compatibility with Spring Boot 3.x, using Java 21)
- PostgreSQL running in a Docker container ID `03d20bbdf899e38206e4d0f5ae0d9631c00ec32562b1340501d3db0b9062fb44`

Import operational rules:
- The application is **already running** on a local server. **Never attempt to start, stop, or restart it yourself.**
- If a code change requires a restart to take effect, explicitly tell me: "Please restart the Spring Boot application to apply these changes."
- Before considering a task complete, always run `./mvn clean compile` locally and confirm there are no compilation errors. If there are errors, fix them first.
- To execute any Maven command, the environment must be set as follows:
  `$env:JAVA_HOME = "C:\Users\tmtmt\.jdks\openjdk-21.0.1"`
- **Strictly Minimal Implementation**: Only implement what is explicitly requested. Do not add "extra" or "helper" functions (like `getBySlug` if only `getById` is asked) even if you think they might be useful later. Unused code is technical debt.

Code style guidelines:
- Write clean, readable code that looks natural and human-written.
- Always response the ResponseObject pattern for REST API responses, encapsulating data, status, and messages.
- Use CustomerException and ErrorCode to handle exception handling.
- **Do not add any comments** in the code unless strictly necessary for clarity in complex logic (prefer self-explanatory code).
- Do not create or suggest new Markdown files, documentation, or explanatory notes outside of direct code changes — I already have full context from previous messages.

When proposing changes:
- Show only the modified/added files with full paths.
- Use proper diff format (or clearly indicate new/updated sections).
- Be concise and focused solely on implementing the requested requirement.
- **No speculative coding**: Only write code that is directly called or required by the current task. Do not pre-emptively add methods for future use cases.

Commands:
- Generate seed-data: `Get-Content src/main/resources/seed-data.sql | docker exec -i 03d20bbdf899 psql -U hung_admin -d hung_hypebeast_ecommerce`

Stay strictly within scope and follow these rules without exception.

## Issue Tracking

This project uses **bd (beads)** for issue tracking.
Run `bd prime` for workflow context, or install hooks (`bd hooks install`) for auto-injection.

**Quick reference:**
- `bd ready` - Find unblocked work
- `bd create "Title" --type task --priority 2` - Create issue
- `bd close <id>` - Complete work
- `bd sync` - Sync with git (run at session end)

For full workflow details: `bd prime`

## Landing the Plane (Session Completion)

**When ending a work session**, you MUST complete ALL steps below. Work is NOT complete until `git push` succeeds.

**MANDATORY WORKFLOW:**

1. **File issues for remaining work** - Create issues for anything that needs follow-up
2. **Run quality gates** (if code changed) - Tests, linters, builds
3. **Update issue status** - Close finished work, update in-progress items
4. **PUSH TO REMOTE** - This is MANDATORY:
   ```bash
   git pull --rebase
   bd sync
   git push
   git status  # MUST show "up to date with origin"
   ```
5. **Clean up** - Clear stashes, prune remote branches
6. **Verify** - All changes committed AND pushed
7. **Hand off** - Provide context for next session

**CRITICAL RULES:**
- Work is NOT complete until `git push` succeeds
- NEVER stop before pushing - that leaves work stranded locally
- NEVER say "ready to push when you are" - YOU must push
- If push fails, resolve and retry until it succeeds
