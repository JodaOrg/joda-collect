
echo "## setup..."
git config --global user.name "Stephen Colebourne (CI)"
git config --global user.email "scolebourne@joda.org"
cd target

echo "## clone..."
git clone https://${GITHUB_TOKEN}@github.com/JodaOrg/jodaorg.github.io.git
cd jodaorg.github.io
git status

echo "## copy..."
rm -rf joda-collect/
cp -R ../site joda-collect/

echo "## update..."
git add -A
git status
git commit --message "Update joda-collect from Travis: Build $TRAVIS_BUILD_NUMBER"

echo "## push..."
git push origin master

echo "## done"
