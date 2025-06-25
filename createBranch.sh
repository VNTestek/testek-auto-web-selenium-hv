#!/bin/bash

# Variables
EMAIL_LIST=("vntestek@gmail.com" "email2@example.com" "email3@example.com")
REPO_URL="https://github.com/VNTestek/testek-java-basic-training"
DISCORD_WEBHOOK_URL="https://discord.com/api/webhooks/1322148824445227060/5sKvkAgBFdjSTQg5skAqzi8An502_n7Vm9du324HA3u2QUoP-SaPtfm6DuXypZ0wIAWw"
DISCORD_THREAD_ID="1322149136610496574"

# Function to create a branch from email
create_branch_from_email() {
  local email=$1
  local branch_name=$(echo $email | cut -d'@' -f1)

  # Clone the repository
  #  git clone $REPO_URL
  #  cd testek-java-basic-training

  # Create a new branch from main
  git checkout -b $branch_name origin/main

  # Push the new branch to the remote repository
  git push origin $branch_name

  # Send email notification
  # echo -e "Hi $email,\n\nWe created the branch '$branch_name' for you successfully.\n\nThanks." | mail -s "New Branch Created" $email

   # Share information to Discord thread
   local message="Hi @$email, A new branch '$branch_name' has been created. Repository link: $REPO_URL/tree/$branch_name"
   curl -H "Content-Type: application/json" -X POST -d "{\"content\": \"$message\"}" "$DISCORD_WEBHOOK_URL?thread_id=$DISCORD_THREAD_ID"
  # Clean up
  #  cd ..
  #  rm -rf testek-java-basic-training
}

# Iterate over the email list and create branches
for EMAIL in "${EMAIL_LIST[@]}"; do
  create_branch_from_email $EMAIL
done

echo "Branches created and notifications sent."