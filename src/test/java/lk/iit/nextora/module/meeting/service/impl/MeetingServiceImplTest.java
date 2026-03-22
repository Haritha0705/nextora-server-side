package lk.iit.nextora.module.meeting.service.impl;
import lk.iit.nextora.common.dto.PagedResponse;
import lk.iit.nextora.common.enums.MeetingStatus;
import lk.iit.nextora.common.exception.custom.BadRequestException;
import lk.iit.nextora.common.exception.custom.ResourceNotFoundException;
import lk.iit.nextora.config.security.SecurityService;
import lk.iit.nextora.module.auth.entity.AcademicStaff;
import lk.iit.nextora.module.auth.entity.Student;
import lk.iit.nextora.module.auth.repository.AcademicStaffRepository;
import lk.iit.nextora.module.auth.repository.StudentRepository;
import lk.iit.nextora.module.meeting.dto.request.*;
import lk.iit.nextora.module.meeting.dto.response.MeetingResponse;
import lk.iit.nextora.module.meeting.entity.Meeting;
import lk.iit.nextora.module.meeting.mapper.MeetingMapper;
import lk.iit.nextora.module.meeting.repository.Meetipackage lk.iitimport lk.iit.nextora.infrastructure.notification.service.MeetingNotificationService;
import org.junit.jupiter.api.DisplayName;
import org.juimport lk.iit.nextora.common.dto.PagedResponse;
impiimport lk.iit.nextora.common.enums.MeetingStatEximport lk.iit.nextora.common.exception.cusimport oimport lk.iit.nextora.common.exception.custom.ResourceNotFoundExcn;import lk.iit.nextora.config.security.SecurityService;
import lk.iit.nek.import lk.iit.nextora.import org.springframework.data.dimport lk.iit.nextora.module.auth.entity.Student;
impoaiimport lk.iit.nextora.module.auth.repository.Acartimport lk.iit.nextora.module.auth.repository.StudentRepository;
impoj.import lk.iit.nextora.module.meeting.dto.request.*;
import lk..aimport lk.iit.nextora.module.meeting.dto.response.g.import lk.iit.nextora.module.meeting.entity.Meeting;
import lk.ii@Eimport lk.iit.nextora.module.meeting.mapper.Meetingtiimport lk.iit.nextora.module.meeting.repository.Meetipack
 import org.junit.jupiter.api.DisplayName;
import org.juimport lk.iit.nextora.common.dto.PagedResponse;
impiimport lk.iit.nextora.common.enums.MeetingStacimport org.juimport lk.iit.nextora.commo Simpiimport lk.iit.nextora.common.enums.MeetingStatEximport ppimport lk.iit.nek.import lk.iit.nextora.import org.springframework.data.dimport lk.iit.nextora.module.auth.entity.Student;
impoaiimport lk.iit.nextora.module.auth.repository.Acartimport lk.iit.nextora.module.auth.repositoretimpoaiimport lk.iit.nextora.module.auth.repository.Acartimport lk.iit.nextora.module.auth.repository.StudentRepository;
itiimpoj.import lk.iit.nextora.module.meeting.dto.request.*;
import lk..aimport lk.iit.nextora.module.meeting.dto.responsatimport lk..aimport lk.iit.nextora.module.meeting.dto.resd(import lk.ii@Eimport lk.iit.nextora.module.meeting.mapper.Meetingtiimport lk.iit.nextora.module.meeting.repository.tu import org.junit.jupiter.api.DisplayName;
import org.juimport lk.i            lecturer.setId(lecturerId);
            lecturimport org.juimport lk.iit.nextora         impiimport lk.iit.nextora.common.enums.MeetingStacimpoeetingSimpoaiimport lk.iit.nextora.module.auth.repository.Acartimport lk.iit.nextora.module.auth.repositoretimpoaiimport lk.iit.nextora.module.auth.repository.Acartimport lk.iit.nextora.module.auth.repository.StudentRepository;
itiimpoj.import lk.iit.nextora.module.meetin).thenReturitiimpoj.import lk.iit.nextora.module.meeting.dto.request.*;
import lk..aimport lk.iit.nextora.module.meeting.dto.responsatimport lk..aimport lk.iit.nextora.module.meeting.dto.resd(import lk.ii@Eimport lk.iit.nextora.moepimport lk..aimport lk.iit.nextora.module.meeting.dto.responmeimport org.juimport lk.i            lecturer.setId(lecturerId);
            lecturimport org.juimport lk.iit.nextora         impiimport lk.iit.nextora.common.enums.MeetingStacimpoeetingSimpoaiimport lk.iit.nextora.module.auth.repository.Acartimport lk.iit.nextora.mod        @Disp            lecturimport org.juimport lk.iit.nextora        voiditiimpoj.import lk.iit.nextora.module.meetin).thenReturitiimpoj.import lk.iit.nextora.module.meeting.dto.request.*;
import lk..aimport lk.iit.nextora.module.meeting.dto.responsatimport lk..aimport lk.iit.nextora.module.meeting.dto.resd(import lk.ii@Eimport lk.iit.nextora.moepimport lk..aimport lk.iit.nextora.module.meeting.dto.responmeimpoetimport lk..aimport lk.iit.nextora.module.meeting.dto.responsatimport lk..aimport lk.iit.nextora.mo            when(s            lecturimport org.juimport lk.iit.nextora         impiimport lk.iit.nextora.common.enums.MeetingStacimpoeetingSimpoaiimport lk.iit.nextora.module.auth.repository.Acartimport lk.iit.nextora.mod        @Disp            lecturimport org.juimport lk.iit.nextora        voiditixcimport lk..aimport lk.iit.nextora.module.meeting.dto.responsatimport lk..aimport lk.iit.nextora.module.meeting.dto.resd(import lk.ii@Eimport lk.iit.nextora.moepimport lk..aimport lk.iit.nextora.module.meeting.dto.responmeimpoetimport lk..aimport lk.iit.nextora.module.meeting.dto.responsatimport lk..aimport lk.iit.nextora.mo            when(s            lecturimport org.juimport lk.iit.nextor               .location("Room 201").build();
            Meeting meeting = Meeting.builder().id(1L).status(MeetingStatus.PENDING).build();
            Meeting scheduled = Meeting.builder().id(1L).status(MeetingStatus.SCHEDULED).build();
            MeetingResponse response = MeetingResponse.builder().id(1L).status(MeetingStatus.SCHEDULED).build();
            when(meetingRepository.findById(1L)).thenReturn(Optional.of(meeting));
            when(meetingRepository.save(any())).thenReturn(scheduled);
            when(meetingMapper.toResponse(scheduled)).thenReturn(response);
            MeetingResponse result = meetingService.acceptMeetingRequest(1L, request);
            assertThat(result.getStatus()).isEqualTo(MeetingStatus.SCHEDULED);
            verify(meetingNotificationService).notifyMeetingAccepted(any());
        }
    }
    @Nested
    @DisplayName("rejectMeetingRequest")
    class RejectMeetingRequestTests {
        @Test
        @DisplayName("Should reject meeting with reason")
        void rejectMeet            Meeting meeting = Meeting.builee            Meeting scheduled = Meeting.builder().id(1L).status(MeetingStatus.SCHEDULED).buion            MeetingResponse response = MeetingResponse.builder().id(1L).status(MeetingStatus.SCHDI            when(meetingRepository.findById(1L)).thenReturn(Optional.of(meeting));
            when(meetingRe                when(meetingRepository.save(any())).thenReturn(scheduled);
          gS            when(meetingM            when(meetingRepository.findById(1L            MeetingResponse result = meetingService.acceptMeetingRequest(1ve            assertThat(result.getStatus()).isEqualTo(MeetingStatus.SCHEDULED);
      he            verify(meetingNotificationService).notifyMeetingAccepted(any());
Me        }
    }
    @Nested
    @DisplayName("rejectMeetingRequest")
    clal    }
  ng    us    @Displ;
    class RejectMeetingRequestTests {
 er        @Test
        @DisplayName("              }
        void rejectMeet            Meeting meeting =     c            when(meetingRe                when(meetingRepository.save(any())).thenReturn(scheduled);
          gS            when(meetingM            when(meetingRepository.findById(1L            MeetingResponse result = meetingService.acceptMeetingRequest(1ve            assertThat(result.getStatus()).isEqualTo(MeetingStatus.SCHEDULED);
 Im          gS            when(meetingM            when(meetingRepository.findById(1L            Meet()      he            verify(meetingNotificationService).notifyMeetingAccepted(any());
Me        }
    }
    @Nested
    @DisplayName("rejectMeetingRequest")
    clal    }
  ng    us    @Displ;
    class RejectMeetingRequestTests {
 er   inMe        }
    }
    @Nested
    @DisplayName("rejectMeetingRequest")
    clal   ing    }
    tM    ti    @Displ(p    clal              assertThat(result).  ng    us  
     class Rejec    @Ne er        @Test
        @DisplayNamng       class Comp        void reject        @Test
               gS            when(meetingM            when(meetingRepository.findById(1L            MeetingResponse result = meetingService.acceptMeetingRequest(1us Im          gS            when(meetingM            when(meetingRepository.findById(1L            Meet()      he            verify(meetingNotificationService).notifyMeetingAccepted(any());
Me        }
    }
    @Nested
    @DisplayNam    Me        }
    }
    @Nested
    @DisplayName("rejectMeetingRequest")
    clal    }
  ng    us    @Displ;
    class RejectMeetingRequestTests {
 er   inMe        }
    }
    @Nested
    on    }
    ed    he    @Displsp    cla            MeetingResponse result  ng    us  rv    class RejectMeet1L er   inMe        }
    }
    @NesteSt    }
    @Nested
Me    gS    @DisplLE    clal   ing    }
  }
