# RoomLoop

The Drop-In Events & Micro-Meetup Platform

## Overview

RoomLoop is a casual, link-free micro-event platform. Users create temporary "Rooms" with topics, people get notified, and anyone can hop in when the room is live. It's not a video call app — it's a presence-first coordination tool.

When you want to throw a quick virtual event, a hangout, or a focused collab session without bloated calendar invites or buried links, RoomLoop provides just a room, a time, and a vibe — and people show up.

## Features

### Core Functionality

1. **Create a Room**
   - Set Room Title, Description, Type (Private/Public)
   - Define Time Window (start/end time)
   - Set Max Participants (optional)
   - Tag rooms (Hangout, Work, Brainstorm, Wellness, etc.)
   - Room status automatically transitions: Scheduled → Live → Closed

2. **Invite Others**
   - Private rooms: Add users by username or email
   - Public rooms: Anyone can view/join from explore page
   - Invitations appear in dashboard/notification panel

3. **Join a Live Room**
   - See who's in the room
   - Drop text messages (ephemeral chat)
   - React with emoji bursts
   - View shared topic or pinned idea
   - Leave room anytime

4. **Room History & Stats**
   - Dashboard shows all rooms created or joined
   - Room status, time, participants
   - Short summary of room outcome (optional)
   - Past rooms remain viewable but not joinable

5. **Explore Public Rooms**
   - View currently live public rooms
   - Join rooms with open slots
   - Filter by tag or status
   - See trending rooms based on popularity


## API Routes

The backend provides RESTful API endpoints for:

- User authentication (register, login, logout)
- Room management (create, update, delete, join)
- Message handling within rooms
- User notifications
- Room discovery

## Authentication

Secure login/signup with email or username. All room access is properly scoped to:
- Room creator
- Invited users
- Public (if room is marked open)

Users can only access rooms during their valid time window.

## Technologies

- Node.js
- TypeScript
- Express.js
- MongoDB (or alternative database)
- JWT for authentication

## Demo Credentials

For testing purposes:
- Username: nitya
- Password: misogiai

## Project URL

https://room-looop.netlify.app/
