### Update your project's maven dependencies with a simple command

- Create a rules (xml) file where your exclusions/inclusions are specified.
Here an example to exclude alpha/beta:
```xml
<ruleset comparisonMethod="maven"
  xmlns="http://mojo.codehaus.org/versions-maven-plugin/rule/2.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://mojo.codehaus.org/versions-maven-plugin/rule/2.0.0 http://mojo.codehaus.org/versions-maven-plugin/xsd/rule-2.0.0.xsd">
  <ignoreVersions>
    <ignoreVersion type="regex">.*(([A|a]lpha)|([B|b]eta)|([M|m]ilestone)|(M|m)).*</ignoreVersion>
  </ignoreVersions>
</ruleset>
```

- Run the maven command line:
`$ mvn versions:update-properties -Dmaven.version.rules=file:///<path_to_your_file>/rules`


Then you can do quick update of your dependencies and create a pull request... Enjoy :)

#### Documentation:

- https://www.mojohaus.org/versions-maven-plugin
- https://www.mojohaus.org/versions-maven-plugin/xsd/rule-2.0.0.xsd
