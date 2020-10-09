### Update your project's maven dependencies with a simple command

Usually, when you want to update your maven dependencies version, you do it manually by checking the dependency on https://mvnrepository.com/ or you use the command:
`$ mvn versions:update-properties` but this will update the versions using beta, milestones and so on, which are not acceptable
in production.

To automatically update the properties with only latest released versions, you can use the rule file as following:

- Create a rules (xml) file where your exclusions/inclusions are specified.
Here an example to exclude alpha/beta/milestones/M:
```xml
<ruleset comparisonMethod="maven"
  xmlns="http://mojo.codehaus.org/versions-maven-plugin/rule/2.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://mojo.codehaus.org/versions-maven-plugin/rule/2.0.0 http://mojo.codehaus.org/versions-maven-plugin/xsd/rule-2.0.0.xsd">
  <ignoreVersions>
    <ignoreVersion type="regex">.*(([A|a]lpha)|a|([B|b]eta)|b|([M|m]ilestone)|(M|m)|(rc)).*</ignoreVersion>
  </ignoreVersions>
</ruleset>
```

- Run the maven command line:
`$ mvn versions:update-properties -Dmaven.version.rules=file:///<path_to_your_file>/rules`


Then you can do quick update of your dependencies and create a pull request... Enjoy :)

#### Documentation:

- https://www.mojohaus.org/versions-maven-plugin
- https://www.mojohaus.org/versions-maven-plugin/xsd/rule-2.0.0.xsd

#### Links:
- https://mvnrepository.com/
