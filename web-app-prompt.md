Plan a web front end for this application. The web app should meet the following requirements:

1. SPA - the application should be a Single Page App
2. Typescript - use Typescript as the language
3. Modern UI - use a modern look and feel with modern theme and icons
4. Testable - support both unit and e2e testing
5. Performant - the UI should meet typical modern latency standards for page loading
   and interactions with users.

The webapp will be in the `web` sub-directory. It will interact with the Java
Spring Boot app in the `server` sub-directory accessing the endpoints in
`SixDegreesController` as well as the authentication support provided by Spring
Boot. For now, provide a log in page to get user credentials to provide to the
backend, but ignore user registration for the time being.

The main page of the web application should prompt for the names of two actors.
For now, simply display some of the details of each actor side by side in a 'card'
like interface. For example,

---

## | Actor: [ enter name ] | Actor: [ enter name ] |

| Name: | Name: |
| Birthday: | Birthday: |
| Credits: | Credits: |
| <etc> | |

---

The UI should be async ... after the user enters the first actor name and tabs or clicks
into the input for the second actor, the first actor should be sent to the server
to retrieve details.

The main aspect of the application though is to show a graph of the relationship(s)
if any of the two actors. Research graph UIs available. One possibility is the
graph library used in the Quartz project: https://github.com/jackyzha0/quartz.git.
I want the graph to show the two actors and the nodes linking them based on their
acting credits.

Write this plan to a file in the current directory e.g. webapp-plan.md.

Ask clarifying questions as needed to help define this plan.
